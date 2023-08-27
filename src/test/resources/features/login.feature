Feature: login features

  Background:
    Given User opens the browser and goes to the home page
    When User goes to login page from home page

  @loginwithemptyorwrongemail
  Scenario: Try to log-in with empty or wrong email
    And User fills login fields
      | Email  | Password |
      | [blank] | [blank] |
    And User try to login they account
    Then User sees warning message "Lütfen geçerli bir e-posta adresi giriniz."

  @loginwithemptypassword
  Scenario: Try to log-in with empty password
    And User fills login fields
      | Email                        | Password |
      | kaantrendyoltester@gmail.com | [blank]  |
    And User try to login they account
    Then User sees warning message "Lütfen şifrenizi giriniz."

  @loginwithwrongemailorpassword
  Scenario: Try to log-in with wrong email or password
    And User fills login fields
      | Email                        | Password |
      | kaantrendyoltester@gmail.com | 123qwe   |
    And User try to login they account
    Then User sees warning message "E-posta adresiniz ve/veya şifreniz hatalı."

  @loginwithcorrectcredentials
  Scenario: Try to log-in with correct credentials
    And User fills login fields
      | Email | Password |
      | VALID | VALID    |
    And User try to login they account
    Then User logins successfully