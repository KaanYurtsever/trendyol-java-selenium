package com.trendyol.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MultiplicationApi extends ApiTests {

    @Test(priority = 4, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "multiplicationData")
    public void testMultiplicationEndpoint(JSONObject params, int expectedResult) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/multiplication")
                        .then()
                        .statusCode(201)
                        .body("result", equalTo(expectedResult))
                        .body("user", equalTo(USERNAME))
                        .log().all()
                        .extract().response();

        //int actualResult = response.jsonPath().getInt("result");
        //Assert.assertEquals(actualResult, expectedResult, "Incorrect multiplication result");
        //Assert.assertEquals(response.jsonPath().getString("user"), USERNAME, "Incorrect user");
    }

    @Test(priority = 4, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"})
    public void testMultiplicationEndpointWithInvalidParameters() {
        int[] params = {2, 3, 6, 5, -2, 4};

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/multiplication")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid parameters"), "Incorrect error message for bad request");
    }

    @Test(priority = 4, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "multiplicationData")
    public void testMultiplicationEndpointWithInvalidCredentials(JSONObject params) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("username", "invalid_username")
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/multiplication")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid credentials"), "Incorrect error message for bad request");
    }

    @DataProvider(name = "multiplicationData")
    public Object[][] getMultiplicationData() {
        return new Object[][] {
                { createParamsJSON(3, 2, 3), 18 },
                { createParamsJSON(4, 5, 2), 40 },
                { createParamsJSON(4, 5, 2), 40 },
                { createParamsJSON(3, -4, 2), 24 },
                { createParamsJSON(6, -2, -1), 12 },
                { createParamsJSON(-4, -5, -2), -40 },
                { createParamsJSON(4, 5, 0), 0 },
        };
    }

    private JSONObject createParamsJSON(int number1, int number2, int number3) {
        JSONObject params = new JSONObject();
        params.put("number1", number1);
        params.put("number2", number2);
        params.put("number3", number3);
        return params;
    }
}
