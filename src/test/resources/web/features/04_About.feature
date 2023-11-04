@web
Feature: ABOUT

  Background:
    Given user is on homepage

  @about-positive
  Scenario: user visit about us page
    When user click About us menu
    Then user should see information about the company

  @about-positive
  Scenario: user return to homepage from about us page by click close button
    When user click About us menu
    And user click close button
    Then user will be directed back to homepage