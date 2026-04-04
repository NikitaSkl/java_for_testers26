package com.example.gorest.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    final static String TOKEN = System.getProperty("api.token");

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.baseUri");
        RestAssured.basePath = System.getProperty("api.basePath");
    }
}
