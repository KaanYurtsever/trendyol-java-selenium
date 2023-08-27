package com.trendyol.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SubtractionApi extends ApiTests {

    @Test(priority = 3, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "subtractionData")
    public void testSubtractionEndpoint(HashMap<String, Integer> params, int expectedResult) {
        Response response =
                given()
                        .contentType("application/json")
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/subtraction")
                        .then()
                        .statusCode(201)
                        .header("Content-Type", "application/json; charset=utf-8")
                        .body("number1", equalTo(params.get("number1")))
                        .body("number2", equalTo(params.get("number2")))
                        .body("result", equalTo(expectedResult))
                        .body("user", equalTo(USERNAME))
                        .log().all()
                        .extract().response();

        //Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8", "Incorrect content type");
        //int actualResult = response.jsonPath().getInt("result");
        //Assert.assertEquals(actualResult, expectedResult, "Incorrect subtraction result");
        //Assert.assertEquals(response.jsonPath().getInt("number1"), params.get("number1"), "Incorrect number1");
        //Assert.assertEquals(response.jsonPath().getInt("number2"), params.get("number2"), "Incorrect number2");
        //Assert.assertEquals(response.jsonPath().getString("user"), USERNAME, "Incorrect user");
    }

    @Test(priority = 3, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"})
    public void testSubtractionEndpointWithInvalidParameters() {
        int[] params = {2, 3, 6, 5, -2, 4};

        Response response =
                given()
                        .contentType("application/json")
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                        .queryParams("param", params)
                        .when()
                        .post("/subtraction")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid parameters"), "Incorrect error message for bad request");
    }

    @Test(priority = 3, dependsOnMethods = {"verifyUserEndpointReturnsCorrectUser"}, dataProvider = "subtractionData")
    public void testSubtractionEndpointWithInvalidCredentials(HashMap<String, Integer> params) {
        Response response =
                given()
                        .contentType("application/json")
                        .header("username", USERNAME)
                        .header("password", "")
                        .queryParams("param", params)
                        .when()
                        .post("/subtraction")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        Assert.assertTrue(errorMessage.contains("Invalid credentials"), "Incorrect error message for bad request");
    }

    @DataProvider(name = "subtractionData")
    public Object[][] getSubtractionData() {
        return new Object[][] {
                { createParamsMap(5, 2), 3 },
                { createParamsMap(10, 7), 3 },
                { createParamsMap(-5, 4), -9 },
                { createParamsMap(-2, 3), -5 },
                { createParamsMap(-4, -6), 2 },
                { createParamsMap(-7, -3), -4 },
                { createParamsMap(3, -2), 5 },
                { createParamsMap(2, -4), 6 },
                { createParamsMap(0, 7), -7 },
                { createParamsMap(7, 0), 7 },
                { createParamsMap(0, -2), 2 },
                { createParamsMap(-2, 0), -2 },
        };
    }

    private HashMap<String, Integer> createParamsMap(int number1, int number2) {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("number1", number1);
        params.put("number2", number2);
        return params;
    }
}
