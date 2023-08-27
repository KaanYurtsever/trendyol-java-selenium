package com.trendyol.step_definitions;

import com.trendyol.steps.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;

public class SearchProductStepDef {

    HomePageSteps homePageSteps = new HomePageSteps();
    SearchPageSteps searchPageSteps = new SearchPageSteps();
    ProductPageSteps productPageSteps= new ProductPageSteps();
    FavoritesPageSteps favoritesPageSteps = new FavoritesPageSteps();
    BasketPageSteps basketPageSteps = new BasketPageSteps(productPageSteps);

    @When("User search product {string}")
    public void userSearchProduct(String productName) {
        homePageSteps.searchProduct(productName);
    }

    @And("User filters brand name {string}")
    public void userFiltersBrandName(String brandName) {
        searchPageSteps.filterBrandName(brandName);
    }

    @And("User chooses filter {string}")
    public void userChoosesFilter(String filterName) {
        searchPageSteps.chooseFilterName(filterName);
    }

    @And("User filters lowest price {string} and highest price {string}")
    public void userFiltersLowestPriceAndHighestPrice(String lowestPrice, String highestPrice) throws ParseException {
        searchPageSteps.filterPriceRange(lowestPrice, highestPrice);
    }

    @And("User clicks first product")
    public void userClicksFirstProduct() {
        searchPageSteps.clickFirstSearchedProduct();
    }

    @When("User is tab {int}")
    public void userIsTab(int tabNo) {
        homePageSteps.setSwitchPage(tabNo);
    }

    @And("User adds desired product to the basket")
    public void userAddsDesiredProductToTheBasket() {
        productPageSteps.addProductToBasket();
        productPageSteps.checkProductAddedToBasket();
    }

    @And("User goes to the basket page")
    public void userGoesToTheBasketPage() {
        homePageSteps.goToMyBasket();
    }

    @Then("User sees added product on the basket page")
    public void userSeesAddedProductOnTheBasketPage() {
        basketPageSteps.clickTooltip();
        basketPageSteps.checkDesiredProductAddedToBasketPage();
    }

    @And("User adds desired product to the favorites")
    public void userAddsDesiredProductToTheFavorites() {
        productPageSteps.addProductToFavorites();
    }

    @And("User goes to the favorites page")
    public void userGoesToTheFavoritesPage() {
        homePageSteps.goToFavorites();
    }


    @And("User adds desired product to the basket from favorites page")
    public void userAddsDesiredProductToTheBasketFromFavoritesPage() {
        favoritesPageSteps.addProductToBasket();
    }

    @Then("User checks products images")
    public void userChecksProductsImages() {
        homePageSteps.checkProductsImages();
    }
}
