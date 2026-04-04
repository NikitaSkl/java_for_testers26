package com.example.gorest.tests;

import com.example.gorest.pojo.UserRequestData;
import com.example.gorest.pojo.UserResponseData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostUsersTests extends TestBase {
    private Integer createdUserId;

    @ParameterizedTest
    @MethodSource("com.example.gorest.provider.UserRequestDataProviders#randomRequestUserProvider")
    public void postToAddNewUser(UserRequestData randomUser) {
        UserResponseData responseData = given()
                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .body(randomUser)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo(randomUser.getName()))
                .body("email", equalTo(randomUser.getEmail()))
                .body("gender", equalTo(randomUser.getGender()))
                .body("status", equalTo(randomUser.getStatus()))
                .extract()
                .as(UserResponseData.class);

        createdUserId = responseData.getId();

        UserResponseData fetchedUser = given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", createdUserId)
                .when()
                .get("/users/{userId}")
                .then()
                .log().ifError()
                .statusCode(200)
                .extract()
                .as(UserResponseData.class);

        Assertions.assertEquals(responseData, fetchedUser);

    }

    @ParameterizedTest
    @MethodSource("com.example.gorest.provider.UserRequestDataProviders#invalidRequestUserProvider")
    public void postToAddInvalidUser(UserRequestData invalidUser, String emptyField, String errorMessage) {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .body(invalidUser)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(422)
                .body("[0].field", equalTo(emptyField))
                .and()
                .body("[0].message", equalTo(errorMessage));
    }

    @AfterEach
    public void cleanup() {
        if (createdUserId != null) {
            given()
                    .header("Authorization", "Bearer " + TOKEN)
                    .pathParam("userId", createdUserId)
                    .when()
                    .delete("/users/{userId}")
                    .then()
                    .statusCode(204);
        }
    }
}
