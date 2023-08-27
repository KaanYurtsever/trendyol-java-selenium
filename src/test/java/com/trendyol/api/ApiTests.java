package com.trendyol.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class ApiTests {

    public static final String API_URL = "http://api.trendyol.com";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = API_URL;
    }

    @Test(priority = 1)
    public void verifyUserEndpointReturnsCorrectUser() {
        String forgeryToken = generateForgeryToken();

        JSONObject requestBody = new JSONObject();
        requestBody.put("username", USERNAME);
        requestBody.put("password", PASSWORD);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("ForgeryToken", forgeryToken)
                        .body(requestBody.toString())
                        .when()
                        .post("/user")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        Assert.assertEquals(response.jsonPath().getString("user"), USERNAME, "Incorrect username in user endpoint response");
    }

    private String generateForgeryToken() {
        return "yBo12gZP0i7FpE-0nqy?kW869JcpI?lT9bc96ovt!2ZVt-GORLQL6qantvoA!Sbn";
    }
}

