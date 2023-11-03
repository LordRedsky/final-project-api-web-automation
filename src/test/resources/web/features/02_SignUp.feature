@web
Feature: SIGNUP

  Background:
    Given user is on homepage
    When user click signup menu

  @signup-positive
  Scenario:  user signup with unregistered username and password
    And user input username with "generate_new_username"
    And user input password with "generate_new_password"
    And user click signup button
    Then user will get a message said "Sign up successful."
    And user will be directed back to homepage

  @signup-negative
  Scenario Outline: user signup with various negative test
    And user input username with "<username>"
    And user input password with "<password>"
    And user click signup button
    Then user will get a message said "<error_message>"
    Examples:
      | username      | password          | error_message                          |
      | existing_user | existing_password | This user already exist.               |
      |               |                   | Please fill out Username and Password. |
      | random_user   |                   | Please fill out Username and Password. |
      |               | random_password   | Please fill out Username and Password. |