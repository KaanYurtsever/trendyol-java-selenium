package com.trendyol.steps;

import com.trendyol.pages.BasketPage;
import com.trendyol.utilities.BrowserUtils;
import org.junit.Assert;

public class BasketPageSteps extends BrowserUtils {

    private final ProductPageSteps productPageSteps;
    public String merchantIdBasket;

    public BasketPageSteps(ProductPageSteps productPageSteps) {
        this.productPageSteps = productPageSteps;
    }

    public void clickTooltip(){
        if(isTheElementPresent((BasketPage.TOOLTIP_OK_BUTTON), WAITTIME_TOO_SMALL)){
            clickElement(BasketPage.TOOLTIP_OK_BUTTON);
        }
    }

    public void checkDesiredProductAddedToBasketPage() {
        String productName = productPageSteps.getMerchantId();
        String basketProduct =  getAttributeOfElement(BasketPage.PRODUCT_NAME, "href");
        merchantIdBasket = extractProductId(basketProduct, "merchantId=");
        Assert.assertEquals("Your product doesn't match", productName, merchantIdBasket);
    }
}
