package com.trendyol.step_definitions;

import com.trendyol.steps.HomePageSteps;
import com.trendyol.steps.LogInPageSteps;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;

public class LogInStepDef {

    HomePageSteps homePageSteps = new HomePageSteps();
    LogInPageSteps logInPageSteps =new LogInPageSteps();

    @Given("User opens the browser and goes to the home page")
    public void userOpensTheBrowserAndGoesToTheHomePage() {
        homePageSteps.openHomePage();
    }

    @When("User goes to login page from home page")
    public void userGoesToLoginPageFromHomePage() {
        homePageSteps.clickLogInButton();
    }

    @And("User fills login fields")
    public void userFillsLoginFields(List<Map<String, String>> elementList) {
        Map<String, String> parameters = elementList.get(0);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            logInPageSteps.fillLogInFields(entry.getKey(), entry.getValue()).run();
        }
    }

    @And("User try to login they account")
    public void userTryToLoginTheyAccount() {
        logInPageSteps.clickSubmitButton();
    }

    @Then("User sees warning message {string}")
    public void userSeesWarningMessage(String warningMsg) {
        logInPageSteps.checkWarningMessage(warningMsg);
    }

    @Then("User logins successfully")
    public void userLoginsSuccessfully() {
        homePageSteps.checkLogInSuccessful();
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String listOfStringListsType(String cell) {
        return cell;
    }

}
