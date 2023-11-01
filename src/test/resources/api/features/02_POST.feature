@api
Feature: POST

  Background:
    Given all request header is properly setup

  @post-positive
  # requred fields is only first name, last name and email
  Scenario: POST: create new user with required body fields only
    And user prepare "required fields" body for "POST" method to:
      | firstName | redsky           |
      | lastName  | raven            |
      | email     | raven24@mail.com |
    When user send a "POST" request to the "create" endpoint
    Then status code should be 200
    And the response should be match with "post_required_fields.json"
    And the response body "id" will be named as "reusableID_01" and will be used for next test

  @post-positive
  Scenario: POST: create new user with full body fields
    And user prepare "full fields" body for "POST" method to:
      | title       | mr                                                |
      | firstName   | lord                                              |
      | lastName    | yondaime                                          |
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
    When user send a "POST" request to the "create" endpoint
    Then status code should be 200
    And the response should be match with "post_full_fields.json"
    And the response body "id" will be named as "reusableID_02" and will be used for next test

  @post-negative
  Scenario: POST: create new user with registered email
    And user prepare "required fields" body for "POST" method to:
      | firstName | redsky           |
      | lastName  | raven            |
      | email     | raven24@mail.com |
    When user send a "POST" request to the "create" endpoint
    Then status code should be 400
    And the response body should contain:
      | error      | BODY_NOT_VALID     |
      | data.email | Email already used |

  @post-negative
  Scenario: POST: create new user with email is empty on required fields body
    And user prepare "required fields" body for "POST" method to:
      | firstName | redsky |
      | lastName  | raven  |
    When user send a "POST" request to the "create" endpoint
    Then status code should be 400
    And the response body should contain:
      | error      | BODY_NOT_VALID            |
      | data.email | Path `email` is required. |

  @post-negative
  Scenario: POST: create new user with invalid email format (not contain '@')
    And user prepare "required fields" body for "POST" method to:
      | firstName | redsky          |
      | lastName  | raven           |
      | email     | raven24mail.com |
    When user send a "POST" request to the "create" endpoint
    Then status code should be 400
    And the response body should contain:
      | error      | BODY_NOT_VALID                             |
      | data.email | Path `email` is invalid (raven24mail.com). |

  @post-negative
   #TITLE VALUE MUST BE 'mr', 'ms', 'mrs', 'miss', 'dr', or empty.
  Scenario: POST: create new user with invalid value of title (title: prof)
    And user prepare "full fields" body for "POST" method to:
      | title       | prof                                              |
      | firstName   | Uzumaki                                           |
      | lastName    | Naruto                                            |
      | picture     | https://randomuser.me/api/portraits/med/men/5.jpg |
      | gender      | male                                              |
      | email       | naruto_uzumaki@mail.com                           |
      | dateOfBirth | 1945-11-17T06:31:57.367Z                          |
      | phone       | 008-123-456-789                                   |
      | country     | Indonesia                                         |
      | city        | Konoha                                            |
      | street      | Jl. Uzumaki                                       |
      | timezone    | +8:00                                             |
      | state       | Konoha Selatan                                    |
    When user send a "POST" request to the "create" endpoint
    Then status code should be 400
    And the response body should contain:
      | error      | BODY_NOT_VALID                                     |
      | data.title | `prof` is not a valid enum value for path `title`. |

  @post-negative
    # maximum value allowed length is 30
    # minimum value allowed length is 2
  Scenario Outline: POST: create new user with firstName and lastName value length boundaries
    And user prepare "required fields" body for "POST" method to:
      | firstName | <name>          |
      | lastName  | <name>          |
      | email     | raven24mail.com |
    When user send a "POST" request to the "create" endpoint
    Then status code should be 400
    And the response body should contain "<error>", with message "<data.firstName>" and "<data.lastName>"

    Examples:
      | name                                 | error          | data.firstName                                                                                            | data.lastName                                                                                           |
      | averylongusernameintheearthandgalaxy | BODY_NOT_VALID | Path `firstName` (`averylongusernameintheearthandgalaxy`) is longer than the maximum allowed length (30). | Path `lastName` (`averylongusernameintheearthandgalaxy`) is longer than the maximum allowed length (30). |
      | a                                    | BODY_NOT_VALID | Path `firstName` (`a`) is shorter than the minimum allowed length (2).                                    | Path `lastName` (`a`) is shorter than the minimum allowed length (2).                                    |