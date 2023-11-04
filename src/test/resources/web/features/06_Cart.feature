@web
Feature: CART

  Background:
    Given user is on homepage

  @cart-positive
  Scenario:
    When user click add to cart button pf these products:
      | Samsung galaxy s6 |
    And user go to cart page
    Then the cart should contain products:
      | Samsung galaxy s6 |
    And the total price is correct