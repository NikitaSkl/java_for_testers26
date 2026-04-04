package com.example.gorest.tests;

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
        List<UserResponseData> usersList = given()
        .header("Authorization", "Bearer " + TOKEN)
                .when()
                .get("/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .extract()
                .as(new TypeRef<List<UserResponseData>>() {
                });

        Assertions.assertTrue(!usersList.isEmpty());

        var userIdForRemoval = usersList.get(new Random()
                .nextInt(usersList.size())).getId();

        given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", userIdForRemoval)
                .when()
                .delete("/users/{userId}")
                .then()
                .statusCode(204);

        given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", userIdForRemoval)
                .when()
                .get("/users/{userId}")
                .then()
                .log().ifError()
                .statusCode(404)
                .body("message", equalTo("Resource not found"));
    }
}
