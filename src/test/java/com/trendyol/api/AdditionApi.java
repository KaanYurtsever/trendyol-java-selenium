package com.trendyol.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AdditionApi extends ApiTests{

    @Test(priority = 2, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "additionData")
    public void testAdditionEndpoint(int[] params, double expectedResult) {
        Response response=
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/add")
                        .then()
                        .statusCode(201)
                        .body("result", equalTo(expectedResult))
                        .body("user", equalTo(USERNAME + " " + PASSWORD))
                        .log().all()
                        .extract().response();

        String jsonString = response.asString();
        Assert.assertTrue(jsonString.contains("Process Done"), "Expected message not found");

        // I wanted to show that we can assert as below as well

        //int actualResult = response.jsonPath().getInt("result");
        //Assert.assertEquals(actualResult, expectedResult, "Incorrect addition result");
        //Assert.assertEquals(response.jsonPath().getString("user"), USERNAME, "Incorrect user");

    }

    @Test(priority = 2, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"})
    public void testAdditionEndpointWithInvalidParameters() {
        int[] params = {1, 2, 3, 4, 5, 6};

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/add")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid parameters"), "Incorrect error message for bad request");
    }

    @Test(priority = 2, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "additionData")
    public void testAdditionEndpointWithInvalidCredentials(int[] params) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", "")
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/add")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid credentials"), "Incorrect error message for bad request");
    }

    @DataProvider(name = "additionData")
    public Object[][] getAdditionData() {
        return new Object[][]{
                {new int[]{1, 2}, 3},
                {new int[]{5, 10}, 15},
                {new int[]{-3, 7}, 4},
                {new int[]{-15, 5}, -10},
                {new int[]{8, -4}, 4},
                {new int[]{6, -9}, -3},
        };
    }
}
