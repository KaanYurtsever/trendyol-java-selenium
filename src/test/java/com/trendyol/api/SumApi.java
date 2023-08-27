package com.trendyol.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SumApi extends ApiTests {

    @Test(priority = 6, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "sumData")
    public void testSumEndpoint(int param, int expectedResult) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .when()
                        .get("/sum?params=" + param)
                        .then()
                        .statusCode(200)
                        .body("result", equalTo(expectedResult))
                        .body("user", equalTo(USERNAME))
                        .log().all()
                        .extract().response();

        //int actualResult = response.jsonPath().getInt("result");
        //Assert.assertEquals(actualResult, expectedResult, "Incorrect sum result");
        //Assert.assertEquals(response.jsonPath().getString("user"), USERNAME, "Incorrect user");
    }

    @Test(priority = 6, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"})
    public void testSumEndpointWithInvalidParameters() {
        int[] params = {5, 10};

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .get("/sum")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid parameters"), "Incorrect error message for bad request");
    }

    @Test(priority = 6, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"})
    public void testSumEndpointWithMinusParameter() {
        int[] param = {-2};

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", param)
                        .when()
                        .get("/sum")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid parameters"), "Incorrect error message for bad request");
    }

    @Test(priority = 6, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "sumData")
    public void testSumEndpointWithInvalidCredentials(int param) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", "invalid_username")
                        .header("password", "invalid_password")
                        .when()
                        .get("/sum?params=" + param)
                        .then()
                        .statusCode(400) // Expecting a bad request
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid credentials"), "Incorrect error message for bad request");
    }

    @DataProvider(name = "sumData")
    public Object[][] getSumData() {
        return new Object[][] {
                { 5, 15 },
                { 4, 10 },
                { 10, 55 },
        };
    }

}
