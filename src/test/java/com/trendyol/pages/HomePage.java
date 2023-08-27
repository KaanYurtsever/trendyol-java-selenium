package com.trendyol.pages;

import org.openqa.selenium.By;

public class HomePage {
    public static final By POP_UP_CLOSE_BUTTON = By.xpath("//div[@id = 'gender-popup-modal']//div[@class= 'modal-close']");
    public static final By ACCEPT_COOKIES_BUTTON = By.id("onetrust-accept-btn-handler");
    public static final By MAIN_LOGIN_BUTTON = By.xpath("//i[@class='i-user-orange hover-icon']");
    public static final By LOGIN_BUTTON = By.className("login-button");
    public static final By SIGNUP_BUTTON = By.xpath("//div[contains(@class, 'signup-button ')]");
    public static final By MY_ACCOUNT_BUTTON = By.xpath("//p[contains(text(), 'Hesabım')]");
    public static final By SEARCH_FIELD = By.xpath("//input[@data-testid='suggestion']");
    public static final By SEARCH_BUTTON = By.xpath("//i[@data-testid='search-icon']");
    public static final By MY_BASKET_BUTTON = By.xpath("//div[@class='account-nav-item basket-preview']");
    public static final By MY_FAVORITES_BUTTON = By.xpath("//a[@class='account-nav-item account-favorites']//p[contains(text(), 'Favori')]");
    public static final By CATEGORIES = By.className("tab-link");
    public static final By FIRST_COMPONENT_ON_CATEGORIES = By.xpath("(//span[@class='image-container']//img)[1]");
    public static final By FIRST_PRODUCT_ON_CATEGORIES = By.xpath("(//div[@class='image-overlay-body'])[1]");
    public static final By COUPON_CLOSE_BUTTON = By.xpath("//div[contains(@class, 'coupon-container')]//a[@class= 'close-button']");
    public static final By ALL_PRODUCTS_BUTTON = By.xpath("//a[contains(text(),'TÜM ÜRÜNLER')]");

    public static final String CATEGORY = "(//a[@class='category-header navbar-first-cap'])[%s]";
    public static final String PRODUCT_ON_CATEGORIES = "(//div[@class='image-overlay-body'])[%s]";
}
