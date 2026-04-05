package com.example.gorest.tests;

import com.example.gorest.clients.UserClient;
import com.example.gorest.pojo.UserResponseData;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class GetUsersTests extends TestBase{
    private static Random random = new Random();
    @Test
    public void getUserList() {
        UserClient.getUsersList()
                .then()
                .log().ifError()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void getUserListFromPage() {
        var pageNumber=random.nextInt(1,10);
        var pageSize=random.nextInt(1,100);
        Response response=UserClient.getUsersListPerPage(pageNumber, pageSize);
        response.then()
                .log().ifError()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void getByExistentUserId() {
        List<UserResponseData> usersList = UserClient.getUsersList()
                .then()
                .log().ifError()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<UserResponseData>>() {
                });

        UserResponseData randomUser = usersList.get(new Random()
                .nextInt(usersList.size()));

        UserClient.getUser(randomUser)
                .then()
                .log().ifError()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void getByNonExistentUserId_returns404() {
        UserClient.getNonExistentUser()
                .then()
                .log().ifError()
                .statusCode(404)
                .body("message", equalTo("Resource not found"));
    }

    @Test
    public void getUserList_returns401() {
        UserClient.getUsersListWithInvalidToken()
                .then()
                .log().ifError()
                .statusCode(401)
                .body("message", equalTo("Invalid token"))
                .body("size()", greaterThan(0));
    }

    @Test
    public void getInactiveUserByExistentId() {
        List<UserResponseData> usersList =UserClient.getUsersListPerPage(random.nextInt(1,10), random.nextInt(1,100))
                .then()
                .log().ifError()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<UserResponseData>>() {
                });

        Assertions.assertTrue(!usersList.isEmpty(), "Users list should not be empty");

        List<UserResponseData> inactiveUsersList = usersList
                .stream()
                .filter(user -> "inactive".equals(user.getStatus()))
                .collect(Collectors.toList());

        Assertions.assertTrue(!inactiveUsersList.isEmpty(), "Inactive users list should not be empty");

        UserResponseData randomUser = inactiveUsersList.get(new Random().nextInt(inactiveUsersList.size()));

        UserClient.getUser(randomUser)
                .then()
                .log().ifError()
                .statusCode(200)
                .body("id", equalTo(randomUser.getId()))
                .body("status", equalTo("inactive"));
    }
}

