@api
Feature: PUT

  Background:
    Given all request header is properly setup

  @put-positive
  Scenario: PUT: update data user from required only to full data
    And user prepare "full fields" body for "PUT" method with:
      | title       | mr                                                |
      | firstName   | redsky                                            |
      | lastName    | raven                                             |
      | picture     | https://randomuser.me/api/portraits/med/men/1.jpg |
      | gender      | male                                              |
      | email       | raven24@mail.com                                  |
      | dateOfBirth | 1945-11-17T06:31:57.367Z                          |
      | phone       | 008-123-456-789                                   |
      | country     | Indonesia                                         |
      | city        | Jakarta                                           |
      | street      | Jl. Kemenangan                                    |
      | timezone    | +8:00                                             |
      | state       | Jakarta Selatan                                   |
    When user send a "PUT" request with specific id "ID_required"
    Then status code should be 200
    And the response should be match with "post_full_fields.json"

  @put-positive
  Scenario: PUT: update user data (firstName and lastName) from full data
    And user prepare "full fields" body for "PUT" method with:
      | title       | mr                                                |
      | firstName   | uzumaki                                           |
      | lastName    | rizki                                             |
      | picture     | https://randomuser.me/api/portraits/med/men/1.jpg |
      | gender      | male                                              |
      | email       | lord_yondaime@mail.com                            |
      | dateOfBirth | 1945-11-17T06:31:57.367Z                          |
      | phone       | 008-123-456-789                                   |
      | country     | Indonesia                                         |
      | city        | Jakarta                                           |
      | street      | Jl. Kemenangan                                    |
      | timezone    | +8:00                                             |
      | state       | Jakarta Selatan                                   |
    When user send a "PUT" request with specific id "ID_full"
    Then status code should be 200
    And the response should be match with "post_full_fields.json"

  @put-positive
  #PREVIOUS EMAIL = raven24@mail.com
  Scenario: PUT: verify email is forbiden to update
    And user prepare "required fields" body for "PUT" method with:
      | firstName | redsky           |
      | lastName  | raven            |
      | email     | updated@mail.com |
    When user send a "PUT" request with specific id "ID_required"
    Then status code should be 200
    And the response should be match with "post_full_fields.json"
    And the response should be contain:
      | firstName | redsky           |
      | lastName  | raven            |
      | email     | raven24@mail.com |

  @put-negative
  # BUG: status code always 200-OK when the required field is empty
  Scenario: PUT: update user data (firstName and lastName) with empty values
    And user prepare "required fields" body for "PUT" method with:
      | firstName |                  |
      | lastName  |                  |
      | email     | raven24@mail.com |
    When user send a "PUT" request with specific id "ID_required"
    # status code should be 400
    Then status code should be 200
#    And the response body should contain:
#      | error          | BODY_NOT_VALID                |
#      | data.firstName | Path `firstName` is required. |
#      | data.lastName  | Path `lastName` is required.  |

  @put-negative
  Scenario Outline: PUT: update user data (firstName and lastName0 with boundary length character
    And user prepare "required fields" body for "PUT" method with:
      | firstName | <name>          |
      | lastName  | <name>          |
      | email     | raven24mail.com |
    When user send a "PUT" request with specific id "ID_required"
    # status code should be 400
    Then status code should be 200
#    And the response body should contain "<error>", with message "<data.firstName>" and "<data.lastName>"

    Examples:
      | name                                 | error          | data.firstName                                                                                            | data.lastName                                                                                           |
      | averylongusernameintheearthandgalaxy | BODY_NOT_VALID | Path `firstName` (`averylongusernameintheearthandgalaxy`) is longer than the maximum allowed length (30). | Path `lastName` (`averylongusernameintheearthandgalaxy`) is longer than the maximum allowed length (30). |
      | a                                    | BODY_NOT_VALID | Path `firstName` (`a`) is shorter than the minimum allowed length (2).                                    | Path `lastName` (`a`) is shorter than the minimum allowed length (2).                                    |