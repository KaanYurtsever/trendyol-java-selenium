package com.trendyol.steps;

import com.trendyol.pages.HomePage;
import com.trendyol.pages.SearchPage;
import com.trendyol.utilities.BrowserUtils;
import com.trendyol.utilities.ConfigurationReader;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomePageSteps extends BrowserUtils {

    String emptyPhoto = "empty_photo_product.png";

    public void openHomePage() {
        goToUrl();
        closePopUp();
        acceptCookies();
    }

    public void goToUrl() {
        navigateToUrl(ConfigurationReader.get("url"));
    }

    public void closePopUp() {
        clickElement(HomePage.POP_UP_CLOSE_BUTTON);
    }

    public void acceptCookies() {
        clickElement(HomePage.ACCEPT_COOKIES_BUTTON);
    }

    public void setSwitchPage(int tabNo) {
        switchTab(tabNo);
    }

    public void clickLogInButton() {
        hoverElement(HomePage.MAIN_LOGIN_BUTTON);
        clickElement(HomePage.LOGIN_BUTTON);
    }

    public void checkLogInSuccessful() {
        Assert.assertTrue("You couldn't login successfully", isTheElementPresent(HomePage.MY_ACCOUNT_BUTTON, WAITTIME_SMALL));
    }

    public void searchProduct(String productName){
        typeElement(HomePage.SEARCH_FIELD, productName, true);
        clickElement(HomePage.SEARCH_BUTTON);
        Assert.assertTrue("Your search is not correct", getPresentElement(SearchPage.SEARCHED_PRODUCT_TITLE).getText().contains(productName));
    }

    public void goToMyBasket() {
        clickElement(HomePage.MY_BASKET_BUTTON);
    }

    public void goToFavorites() {
        clickElement(HomePage.MY_FAVORITES_BUTTON);
    }

    public void checkProductsImages() {
        String folderPath = "screenshots/";
        File folder = new File(folderPath);
        folder.mkdirs();

        for (int i = 1; i <= getPresentElements(HomePage.CATEGORIES).size()-2; i++){
            clickElement(By.xpath(getLocatorFromInt(HomePage.CATEGORY, i)));
            clickElement(HomePage.FIRST_COMPONENT_ON_CATEGORIES);
            if (isTheElementPresent(HomePage.COUPON_CLOSE_BUTTON, WAIT_ONE_SECOND)){
                clickElement(HomePage.COUPON_CLOSE_BUTTON);
            }
            if (isTheElementPresent(HomePage.ALL_PRODUCTS_BUTTON, WAIT_ONE_SECOND)){
                clickElement(HomePage.ALL_PRODUCTS_BUTTON);
            }
            if(isTheElementPresent(SearchPage.OVERLAY_POPUP, WAITTIME_TOO_SMALL)){
                clickElement(SearchPage.EMPTY_SPACE);
            }

            for (int j = 1; j <= 4; j++){

                if(isTheElementPresent((HomePage.FIRST_PRODUCT_ON_CATEGORIES), WAITTIME_TOO_SMALL)){
                By productLocator = By.xpath(getLocatorFromInt(HomePage.PRODUCT_ON_CATEGORIES, j));
                WebElement productElement = getPresentElement(productLocator);
                Assert.assertTrue("Photo does not found", isTheElementPresent(productLocator, WAITTIME_TOO_SMALL));

                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String fileName = "product_" + i + "_" + j + "_" + timestamp + ".png";
                String filePath = folderPath + fileName;

                takeScreenshot(productElement, filePath);

                Assert.assertFalse(compareImages(filePath, emptyPhoto));

            }}
            goToUrl();
        }
    }
}
