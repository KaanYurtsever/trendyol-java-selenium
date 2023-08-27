package com.trendyol.pages;

import org.openqa.selenium.By;

public class SearchPage {
    public static final By SEARCHED_PRODUCT_TITLE= By.xpath("//div[@class= 'srch-rslt-title']");
    public static final By LOWEST_PRICE_FIELD_FILTER = By.xpath("//input[@placeholder= 'En Az']");
    public static final By HIGHEST_PRICE_FIELD_FILTER = By.xpath("//input[@placeholder= 'En Çok']");
    public static final By SEARCH_PRICE_RANGE_BUTTON = By.className("fltr-srch-prc-rng-srch");
    public static final By FIRST_SEARCHED_PRODUCT = By.xpath("(//div[@class='image-overlay-body'])[1]");
    public static final By FIRST_SEARCHED_PRODUCT_PRICE = By.xpath("(//div[@class='prc-box-dscntd'])[1]");
    public static final By NEW_FEATURES_POPUP = By.xpath("//div[@class = 'popup']//span[contains(text(), 'YENİ')]");
    public static final By OVERLAY_POPUP = By.className("overlay");
    public static final By EMPTY_SPACE = By.xpath("//div[@data-fragment-name='Search']");

    public static final String BRAND_NAME_FILTER = "//div[@class= 'fltrs']//div[text()='%s']";
    public static final String FILTER_NAME = "//div[@class= 'fltr-cntnr-ttl-area']//div[text()='%s']";
}
