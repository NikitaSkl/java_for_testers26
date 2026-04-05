package com.example.gorest.tests;

import com.example.gorest.clients.UserClient;
import com.example.gorest.pojo.UserResponseData;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class DeleteUserTests extends TestBase{
    @Test
    public void deleteExistentUser() {
        List<UserResponseData> usersList = UserClient.getUsersList()
                .then()
                .log().ifError()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .extract()
                .as(new TypeRef<List<UserResponseData>>() {
                });

        Assertions.assertTrue(!usersList.isEmpty());

        var userForRemoval = usersList.get(new Random()
                .nextInt(usersList.size()));

        UserClient.deleteUser(userForRemoval)
                .then()
                .statusCode(204);

        UserClient.getUser(userForRemoval)
                .then()
                .log().ifError()
                .statusCode(404)
                .body("message", equalTo("Resource not found"));
    }

}
