Feature: searching and adding products features

  Background:
    Given User opens the browser and goes to the home page
    When User goes to login page from home page
    And User fills login fields
      | Email                        | Password                      |
      | kaantrendyoltester@gmail.com | testerkaantrendyol123deneme   |
    And User try to login they account
    Then User logins successfully

  @addproducttobasket
  Scenario: Add desired product to the basket
    When User search product "Oyuncu Bilgisayarı"
    And User filters brand name "Monster"
    And User chooses filter "Fiyat"
    And User filters lowest price "3000" and highest price "10000"
    And User clicks first product
    When User is tab 1
    And User adds desired product to the basket
    And User goes to the basket page
    Then User sees added product on the basket page

  @addproducttofavorites
  Scenario: Add desired product to the favorites
    When User search product "Gömlek"
    And User clicks first product
    When User is tab 1
    And User adds desired product to the favorites
    And User goes to the favorites page
    And User adds desired product to the basket from favorites page
    And User goes to the basket page
    Then User sees added product on the basket page

