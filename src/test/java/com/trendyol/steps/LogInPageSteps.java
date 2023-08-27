package com.trendyol.steps;

import com.trendyol.pages.LogInPage;
import com.trendyol.utilities.BrowserUtils;
import org.junit.Assert;
import java.util.HashMap;
import java.util.Map;

public class LogInPageSteps extends BrowserUtils {

    public Runnable fillLogInFields(String key, String value) {
        Map<String, Runnable> hashMap = new HashMap<>();
        hashMap.put("Email", () -> fillEmailField(value));
        hashMap.put("Password", () -> fillPasswordField(value));
        return hashMap.get(key);
    }

    private void fillEmailField(String email) {
        typeElement(LogInPage.EMAIL_FIELD, email, true);
    }

    private void fillPasswordField(String password) {
        typeElement(LogInPage.PASSWORD_FIELD, password, true);
    }

    public void clickSubmitButton(){
        clickElement(LogInPage.LOGIN_TO_ACCOUNT_BUTTON);
    }

    public void checkWarningMessage(String warningMsg) {
        Assert.assertEquals("Your warning message is not correct", getPresentElement(LogInPage.WARNING_MESSAGE).getText(), warningMsg);
    }
}
