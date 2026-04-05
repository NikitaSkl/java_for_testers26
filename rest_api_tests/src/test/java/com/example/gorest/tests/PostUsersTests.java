package com.example.gorest.tests;

import com.example.gorest.clients.UserClient;
import com.example.gorest.pojo.UserRequestData;
import com.example.gorest.pojo.UserResponseData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostUsersTests extends TestBase{
    private UserResponseData createdUser;

    @ParameterizedTest
    @MethodSource("com.example.gorest.provider.UserRequestDataProviders#randomUsersProvider")
    public void postToAddNewUser(UserRequestData randomUser) {
        UserResponseData responseData = UserClient.postNewUser(randomUser)
                .then()
                .log().ifError()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo(randomUser.getName()))
                .body("email", equalTo(randomUser.getEmail()))
                .body("gender", equalTo(randomUser.getGender()))
                .body("status", equalTo(randomUser.getStatus()))
                .extract()
                .as(UserResponseData.class);

        createdUser = responseData;

        UserResponseData fetchedUser = UserClient.getUser(responseData)
                .then()
                .log().ifError()
                .statusCode(200)
                .extract()
                .as(UserResponseData.class);

        Assertions.assertEquals(responseData, fetchedUser);

    }

    @ParameterizedTest
    @MethodSource("com.example.gorest.provider.UserRequestDataProviders#invalidUsersProvider")
    public void postToAddInvalidUser(UserRequestData invalidUser, String emptyField, String errorMessage) {
        UserClient.postNewUser(invalidUser)
                .then()
                .log().all()
                .statusCode(422)
                .body("[0].field", equalTo(emptyField))
                .and()
                .body("[0].message", equalTo(errorMessage));
    }

    @AfterEach
    public void cleanup() {
        if (createdUser != null) {
            UserClient.deleteUser(createdUser)
                    .then()
                    .statusCode(204);
        }
    }
}
