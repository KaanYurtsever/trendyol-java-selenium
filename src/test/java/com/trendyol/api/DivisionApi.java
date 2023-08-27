package com.trendyol.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DivisionApi extends ApiTests {

    @Test(priority = 5, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "divisionData")
    public void testDivisionEndpoint(int[] params, double expectedResult) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/division")
                        .then()
                        .statusCode(201)
                        .body("result", equalTo(expectedResult))
                        .body("user", equalTo(USERNAME))
                        .log().all()
                        .extract().response();

        //int actualResult = response.jsonPath().getInt("result");
        //Assert.assertEquals(actualResult, expectedResult, "Incorrect division result");
        //Assert.assertEquals(response.jsonPath().getString("user"), USERNAME, "Incorrect user");
    }

    @Test(priority = 5, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"})
    public void testDivisionEndpointWithInvalidParameters() {
        int[] params = {10, 5, 1, 8, 2, 6};

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/division")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid parameters"), "Incorrect error message for bad request");
    }

    @Test(priority = 5, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"})
    public void testDivisionEndpointWithZeroNumberParameter() {
        int[] params = {1, 0};

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/division")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("You can't divide a number to 0"), "Incorrect error message for bad request");
    }

    @Test(priority = 5, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "divisionData")
    public void testDivisionEndpointWithInvalidCredentials(int[] params) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", "invalid_password")
                        .queryParams("param", params)
                        .when()
                        .post("/division")
                        .then()
                        .statusCode(400) // Expecting a bad request
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid credentials"), "Incorrect error message for bad request");
    }

    @DataProvider(name = "divisionData")
    public Object[][] getDivisionData() {
        return new Object[][] {
                { new int[]{55, 11}, 5 },
                { new int[]{30, 5}, 6 },
                { new int[]{10, -2}, -5 },
                { new int[]{-10, -2}, 5 },
                { new int[]{-15, 3}, -5 },
                { new int[]{10, 4}, 2.5 },
                { new int[]{10, -4}, -2.5 },
                { new int[]{0, 1}, 0 },
        };
    }
}
