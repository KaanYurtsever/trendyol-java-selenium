package com.trendyol.pages;

import org.openqa.selenium.By;

public class LogInPage {
    public static final By EMAIL_FIELD = By.id("login-email");
    public static final By PASSWORD_FIELD = By.id("login-password-input");
    public static final By LOGIN_TO_ACCOUNT_BUTTON = By.xpath("//button[@type='submit']");
    public static final By SIGNUP_BUTTON = By.xpath("//span[contains(text(), 'Üye ')]");
    public static final By LOGIN_TAB = By.id("//button[contains(@class, 'q-secondary q-button-medium q-button tab button mr')]");
    public static final By WARNING_MESSAGE = By.className("message");
}
