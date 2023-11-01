@web
Feature: HOMEPAGE

  Background:
    Given  user is on homepage

  @homepage-positive
  Scenario Outline: HOMEPAGE: show product categories
    When user click "<category>" button
    Then only "<category>" products will be displayed

    Examples:
      | category |
      | Phone    |
      | Laptop   |
      | Monitor  |
