@web
Feature: LOGIN

  Background:
    Given user is on homepage
    When user click login menu

  @login-positive
  Scenario: user login with valid account
    And user login with username with "existing_user"
    And user login with password with "existing_password"
    And user click login button
    Then user will be directed back to homepage
    And user will see an element Welcome "existing_user"

  @login-negative
  Scenario Outline: user login with various negative test scenario
    And user login with username with "<username>"
    And user login with password with "<password>"
    And user click login button
    Then user will get a message said "<error_message>"

    Examples:
      | username                     | password          | error_message                          |
      |                              |                   | Please fill out Username and Password. |
      | existing_user                | wrong_password    | Wrong password.                        |
      | unregistered_username        | existing_password | User does not exist.                   |
      | unregistered_username        | wrong_password    | User does not exist.                   |
      | existing_username_with_space | existing_password | User does not exist.                   |
