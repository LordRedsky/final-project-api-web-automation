@web
Feature: CONTACT

  Background:
    Given user is on homepage
    When user click contact menu

  @contact-positive
  Scenario: user input with valid data
    And user input contact email with "redsky@email.com"
    And user input contact name with "redsky"
    And user input message with "Is the Macbook Pro M1 ready?"
    And user click send message button
    Then user will get a message said "Thanks for the message!!"

  @contact-positive
  Scenario: user return to homepage from contact page by click close button
    And user click close button on contact page
    Then user will be directed back to homepage

  @contact-negative

  #Scenario Testing
   #1 all fields empty
   #2 email fields empty
   #3 name fields empty
   #4 message fields empty
   #5 invalid email format
  #All FAILED because response message always 'Thanks for the message!!'
  Scenario Outline: submit contact form with various negative scenarios
    And user input contact email with "<email>"
    And user input contact name with "<name>"
    And user input message with "<message>"
    And user click send message button
    Then user will get a message said "<actual_message>"

    Examples:
      | email            | name   | message                      | error_message                                             | actual_message           |
      |                  |        |                              | Please fill out Contact Email, Contact Name, and Message. | Thanks for the message!! |
      |                  | redsky | Is the Macbook Pro M1 ready? | Please fill out Contact Email                             | Thanks for the message!! |
      | redsky@email.com |        | Is the Macbook Pro M1 ready? | Please fill out Contact Name                              | Thanks for the message!! |
      | redsky@email.com | redsky |                              | Please fill out message                                   | Thanks for the message!! |
      | redskyemail.com  | redsky | Is the Macbook Pro M1 ready? | Please enter a valid email address.                       | Thanks for the message!! |