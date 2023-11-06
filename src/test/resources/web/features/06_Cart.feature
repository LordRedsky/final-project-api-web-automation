@web
Feature: CART

  Background:
    Given user is on homepage

  @cart-positive
  Scenario: user can add one product to cart
    When user click add to cart button of these products:
      | Samsung galaxy s6 |
    And user go to cart page
    Then the cart should contain products:
      | Samsung galaxy s6 |
    And the total price is correct

  @cart-positive
  Scenario: user can add more than one product to cart
    When user click add to cart button of these products:
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |
      | Nexus 6           |
    And user go to cart page
    Then the cart should contain products:
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |
      | Nexus 6           |
    And the total price is correct

  @cart-positive
  Scenario: user can add the quantity of the products more than one on the cart
    When user click add to cart button 3 times for these products:
      | Nokia lumia 1520 |
    And user go to cart page
    Then total cart item for these product should be 3:
      | Nokia lumia 1520 |
    And the total price is correct

  @cart-positive
  Scenario: user can add the quantity of the products more than one on the cart and can remove one quantity
    When user click add to cart button 3 times for these products:
      | Nokia lumia 1520 |
    And user go to cart page
    And user click delete button 1 times for these products:
      | Nokia lumia 1520 |
    Then total cart item for these product should be 2:
      | Nokia lumia 1520 |
    And the total price is correct

  @cart-positive
  Scenario: user can add the quantity of each products more than one on the cart and can remove one quantity of each
    When user click add to cart button 3 times for these products:
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |
    And user go to cart page
    And user click delete button 1 times for these products:
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |
    Then total cart item for these product should be 2:
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |
    And the total price is correct

  @cart-positive
  Scenario: user can delete one ot the products from cart
    When user click add to cart button of these products:
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |
      | Nexus 6           |
    And user go to cart page
    And user click delete button for these products:
      | Nokia lumia 1520 |
    Then the cart should contain products:
      | Samsung galaxy s6 |
      | Nexus 6           |
    And the total price is correct

  @cart-boundaries
  Scenario: user can add all products to the cart
    When user click add to cart for all products on cart
    And user go to cart page
    Then the cart should contain all products
    And the total price is correct