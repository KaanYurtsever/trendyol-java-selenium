package com.trendyol.step_definitions;

import com.trendyol.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class Hooks {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions action;

    @Before
    public void setUp() {
        driver = Driver.get();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        action = new Actions(driver);
    }

    @After
    public void tearDown(Scenario trendyol) {
        //Take screenshot in allure report
        if (trendyol.isFailed()){
            Allure.addAttachment(trendyol.getName(),new ByteArrayInputStream(((TakesScreenshot)driver)
                    .getScreenshotAs(OutputType.BYTES)));
        }
        Driver.closeDriver();
    }
}
