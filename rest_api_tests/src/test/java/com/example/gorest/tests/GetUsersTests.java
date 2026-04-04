package com.example.gorest.tests;

import com.example.gorest.pojo.UserResponseData;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class GetUsersTests extends TestBase {

    @Test
    public void getUserList() {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .get("/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void getUserListFromPage() {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("page", 1)
                .queryParam("per_page", 15)
                .when()
                .get("/users")
                .then()
                .log().ifError()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void getByExistentUserId() {
        List<UserResponseData> usersList = given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("page", 1)
                .queryParam("per_page", 50)
                .when()
                .get("/users")
                .then()
                .log().ifError()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<UserResponseData>>() {
                });

        UserResponseData randomUser = usersList.get(new Random()
                .nextInt(usersList.size()));

        given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", randomUser.getId())
                .when()
                .get("/users/{userId}")
                .then()
                .log().ifError()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void getByNonExistentUserId_returns404() {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", Integer.MAX_VALUE)
                .when()
                .get("/users/{userId}")
                .then()
                .log().ifError()
                .statusCode(404)
                .body("message", equalTo("Resource not found"));
    }

    @Test
    public void getUserList_returns401() {
        given()
                .header("Authorization", "Bearer " + "Invalid token value")
                .when()
                .get("/users")
                .then()
                .log().all()
                .statusCode(401)
                .body("message", equalTo("Invalid token"))
                .body("size()", greaterThan(0));
    }

    @Test
    public void getInactiveUserByExistentId() {
        List<UserResponseData> usersList = given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("page", 1)
                .queryParam("per_page", 100)
                .when()
                .get("/users")
                .then()
                .log().ifError()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<UserResponseData>>() {
                });

        Assertions.assertTrue(!usersList.isEmpty(), "Users list should not be empty");

        List<UserResponseData> inactiveUsersList = usersList
                .stream()
                .filter(user -> {
                    return "inactive".equals(user.getStatus());
                })
                .collect(Collectors.toList());

        Assertions.assertTrue(!inactiveUsersList.isEmpty(), "Inactive users list should not be empty");

        Integer randomUserId = inactiveUsersList.get(new Random().nextInt(inactiveUsersList.size())).getId();

        given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", randomUserId)
                .when()
                .get("/users/{userId}")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(randomUserId))
                .body("status", equalTo("inactive"));
    }
}

