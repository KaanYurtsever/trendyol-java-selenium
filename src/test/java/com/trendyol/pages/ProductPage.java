package com.trendyol.pages;

import org.openqa.selenium.By;

public class ProductPage {
    public static String ADD_TO_BASKET_SUCCESSFUL_MESSAGE_TEXT = "Ürün Sepete Eklendi!";

    public static final By GOT_IT_POPUP_FOR_CAMPAIGN = By.xpath("//div[@class='campaign-button bold']");
    public static final By ADD_TO_BASKET_BUTTON = By.className("add-to-basket-button-text");
    public static final By ADD_TO_BASKET_SUCCESSFUL_MESSAGE = By.className("product-preview-status-text");
    public static final By PRODUCT_NAME = By.xpath("//div[@class='rvw-cnt']//a");
    public static final By ADD_TO_FAVORITES = By.xpath("//button[@class= 'fv']//i");
}
