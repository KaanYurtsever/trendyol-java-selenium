package com.trendyol.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class Window {
    
    public void openNewPage(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void closePage(WebDriver driver, int windowsId) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(windowsId)).close();
    }

    public void switchPage(WebDriver driver, int windowsId) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(windowsId));
    }

    public void switchTab(WebDriver driver, int tabNo) {
        if (!(driver.getWindowHandles().size() > 1)){
            openNewPage(driver);
        }
        switchPage(driver, tabNo);
    }
}
