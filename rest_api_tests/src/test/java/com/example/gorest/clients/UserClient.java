package com.example.gorest.clients;

import com.example.gorest.pojo.UserPatchRequest;
import com.example.gorest.pojo.UserRequestData;
import com.example.gorest.pojo.UserResponseData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient{
    final static String TOKEN = System.getProperty("api.token");
    public static Response getUsersList(){
        return given()
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .get("/users");
    }
    public static Response getUsersListPerPage(int pageNumber, int pageSize){
        return given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("page", pageNumber)
                .queryParam("per_page", pageSize)
                .when()
                .get("/users");
    }

    public static Response getUser(UserResponseData user) {
        return given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", user.getId())
                .when()
                .get("/users/{userId}");
    }

    public static Response getNonExistentUser() {
        return given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", Integer.MAX_VALUE)
                .when()
                .get("/users/{userId}");
    }

    public static Response getUsersListWithInvalidToken() {
        return given()
                .header("Authorization", "Bearer " + "Invalid token value")
                .when()
                .get("/users");
    }

    public static Response postNewUser(UserRequestData user) {
        return given()
                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users");
    }
    public static Response patchUser(UserResponseData userToPatch, UserPatchRequest userPatchData) {
        return given()
                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .pathParam("userId", userToPatch.getId())
                .body(userPatchData)
                .when()
                .patch("/users/{userId}");
    }

    public static Response deleteUser(UserResponseData userForRemoval) {
        return given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("userId", userForRemoval.getId())
                .when()
                .delete("/users/{userId}");
    }
}
