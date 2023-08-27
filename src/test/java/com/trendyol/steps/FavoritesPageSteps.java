package com.trendyol.steps;

import com.trendyol.pages.FavoritesPage;
import com.trendyol.utilities.BrowserUtils;

public class FavoritesPageSteps extends BrowserUtils {

    public void addProductToBasket(){
        clickElement(FavoritesPage.ADD_TO_BASKET_BUTTON);
    }
}
