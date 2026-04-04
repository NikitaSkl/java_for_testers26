package com.example.gorest.tests;

import com.example.gorest.common.CommonFunctions;
import com.example.gorest.pojo.UserRequestData;
import com.example.gorest.pojo.UserResponseData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostUsersTests extends TestBase {
    private Integer createdUserId;

    @ParameterizedTest
    @MethodSource("randomRequestUserProvider")
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
    @MethodSource("invalidRequestUserProvider")
    public void postToAddInvalidUser(UserRequestData invalidUser) {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .body(invalidUser)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(422)
                .body("field",equalTo("name"))
                .and()
                .body("message",equalTo("can't be blank"));
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

    public static List<UserRequestData> randomRequestUserProvider() {
        Supplier<UserRequestData> randomUserSupplier = () -> new UserRequestData()
                .setName(CommonFunctions.randomString(9))
                .setEmail(String.format("%s@gorest.test", CommonFunctions.randomString(7)))
                .setGender("male")
                .setStatus("active");
        return Stream.generate(randomUserSupplier).limit(3).collect(Collectors.toList());
    }

    public static List<UserRequestData> invalidRequestUserProvider() {
        var list = List.of(new UserRequestData()
                        //.setEmail(String.format("%s@gorest.test", CommonFunctions.randomString(7)))
                        //.setGender("male")
                        //.setStatus("active")
                ,
                new UserRequestData()
                        .setName(CommonFunctions.randomString(7))
                        .setEmail("")
                        .setGender("male")
                        .setStatus("active"));
        return list;
    }
}
