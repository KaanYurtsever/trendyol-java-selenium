package com.trendyol.steps;

import com.trendyol.pages.ProductPage;
import com.trendyol.utilities.BrowserUtils;
import org.junit.Assert;

public class ProductPageSteps extends BrowserUtils {

    public String merchantId;

    public void setMerchantId(){
        String productName1 = getAttributeOfElement(ProductPage.PRODUCT_NAME, "href");
        merchantId = extractProductId(productName1, "merchantId=");
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void addProductToBasket(){
        setMerchantId();
        if(isTheElementPresent(ProductPage.GOT_IT_POPUP_FOR_CAMPAIGN, WAITTIME_TOO_SMALL)){
            clickElement(ProductPage.GOT_IT_POPUP_FOR_CAMPAIGN);
        }
        clickElement(ProductPage.ADD_TO_BASKET_BUTTON);
    }

    public void checkProductAddedToBasket() {
        Assert.assertEquals("You couldn't add your product to the basket",
                getPresentElement(ProductPage.ADD_TO_BASKET_SUCCESSFUL_MESSAGE).getText(), ProductPage.ADD_TO_BASKET_SUCCESSFUL_MESSAGE_TEXT);
    }

    public void addProductToFavorites(){
        setMerchantId();
        if(isTheElementPresent(ProductPage.GOT_IT_POPUP_FOR_CAMPAIGN, WAITTIME_TOO_SMALL)){
            clickElement(ProductPage.GOT_IT_POPUP_FOR_CAMPAIGN);
        }
        clickElement(ProductPage.ADD_TO_FAVORITES);
    }
}
