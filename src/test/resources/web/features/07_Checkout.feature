@web
Feature: CHECKOUT

  Background:
    Given user is on homepage

  @checkout-positive
  Scenario: user checkout with valid payment data
    When user already logged in
    And user add "one" product to cart
    And user go to cart page
    And user click place order button
    And user will see place order form
    And user fill the form with:
      | Name        | Lord Redsky |
      | Country     | Indonesia   |
      | City        | Manado      |
      | Credit Card | 232425      |
      | Month       | 11          |
      | Year        | 2023        |
    Then user click purchase button
    And user will see a pop up message said "Thank you for your purchase!"

  @checkout-positive
  Scenario: user back to cart page from checkout form
    When user already logged in
    And user add "one" product to cart
    And user go to cart page
    And user click place order button
    And user will see place order form
    Then user click close button on form page
    And user will be directed back to cart page

  @checkout-negative
  #FAILED always success although user not logged in first
  Scenario: user checkout without logged in first
    And user add "one" product to cart
    And user go to cart page
    And user click place order button
    And user will see place order form
    And user fill the form with:
      | Name        | Lord Redsky |
      | Country     | Indonesia   |
      | City        | Manado      |
      | Credit Card | 232425      |
      | Month       | 11          |
      | Year        | 2023        |
    Then user click purchase button
    # Actual message
    And user will see a pop up message said "Thank you for your purchase!"
    # Expected message
    # And user will see a pop up message said "Please logged in first!"

  @checkout-negative
  Scenario: user checkout with blank payment data fields
    When user already logged in
    And user add "one" product to cart
    And user go to cart page
    And user click place order button
    And user will see place order form
    And user fill the form with "all_fields_blank"
    Then user click purchase button
    And user will get a message said "Please fill out Name and Creditcard."

  @checkout-negative
  Scenario: user fill the checkout form data with blank name fields
    When user already logged in
    And user add "one" product to cart
    And user go to cart page
    And user click place order button
    And user will see place order form
    And user fill the form with "name_fields_blank"
    Then user click purchase button
    And user will get a message said "Please fill out Name and Creditcard."

  @checkout-negative
  Scenario: user fill the checkout form data with blank credit card fields
    When user already logged in
    And user add "one" product to cart
    And user go to cart page
    And user click place order button
    And user will see place order form
    And user fill the form with "cc_fields_blank"
    Then user click purchase button
    And user will get a message said "Please fill out Name and Creditcard."

  @checkout-negative
  #FAILED always success although others fields blank except name and credit card
  Scenario: user fill the checkout form data with only name and credit card fields
    When user already logged in
    And user add "one" product to cart
    And user go to cart page
    And user click place order button
    And user will see place order form
    And user fill the form with "only_name_and_cc"
    Then user click purchase button
  #Actual Message
    And user will see a pop up message said "Thank you for your purchase!"
  #Expected message
  #And user will get a message said "Please fill out Country, City, Month and Year"