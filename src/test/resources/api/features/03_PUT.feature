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