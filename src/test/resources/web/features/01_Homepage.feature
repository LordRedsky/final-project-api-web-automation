@web
Feature: HOMEPAGE

  Background:
    Given  user is on homepage

  @homepage-positive
  Scenario: HOMEPAGE: validated all menu and button is displayed properly
    Then all menu and button displayed properly
