package com.trendyol.steps;

import com.trendyol.pages.SearchPage;
import com.trendyol.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class SearchPageSteps extends BrowserUtils {

    public void filterBrandName (String brandName){
        clickElement(By.xpath(getLocatorFromString(SearchPage.BRAND_NAME_FILTER, brandName)));
    }

    public void chooseFilterName(String filterName) {
        clickElement(By.xpath(getLocatorFromString(SearchPage.FILTER_NAME, filterName)));
    }

    public void filterPriceRange(String lowestPrice, String highestPrice) throws ParseException {
        typeElement(SearchPage.LOWEST_PRICE_FIELD_FILTER, lowestPrice, true);
        typeElement(SearchPage.HIGHEST_PRICE_FIELD_FILTER, highestPrice, true);
        clickElement(SearchPage.SEARCH_PRICE_RANGE_BUTTON);
        waitSeconds(3);

        int lowestPriceFilter = Integer.parseInt(lowestPrice);
        int highestPriceFilter = Integer.parseInt(highestPrice);
        int firstSearchedProductPrice = getFirstSearchedProductPrice();

        checkPriceInRange(lowestPriceFilter, highestPriceFilter, firstSearchedProductPrice);
    }

    private int getFirstSearchedProductPrice() throws ParseException {
        String priceString = getPresentElement(SearchPage.FIRST_SEARCHED_PRODUCT_PRICE).getText();
        DecimalFormat decimalFormat = new DecimalFormat("#");
        decimalFormat.setParseBigDecimal(true);
        BigDecimal priceValue = (BigDecimal) decimalFormat.parse(priceString.replaceAll("[^\\d,]", ""));
        return priceValue.intValue();
    }

    private void checkPriceInRange(int lowestPriceFilter, int highestPriceFilter, int productPrice) {
        Assert.assertTrue("Your price range is not correct",
                lowestPriceFilter <= productPrice && productPrice <= highestPriceFilter);
    }

    public void clickFirstSearchedProduct(){
        if(isTheElementPresent(SearchPage.OVERLAY_POPUP, WAITTIME_TOO_SMALL)){
            clickElement(SearchPage.EMPTY_SPACE);
        }
            clickElement(SearchPage.FIRST_SEARCHED_PRODUCT);
    }
}
