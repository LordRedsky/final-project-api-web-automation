@api
Feature: GET

  Background:
    Given all request header is properly setup

# TAG
  @get-positive
  Scenario: GET: get list of Tags
    When user send a "GET" request to the "tag" endpoint
    Then status code should be 200
    And the response should be match with "get_list_tag.json"

# USER
  @get-positive
  Scenario: GET: get list of users
    When user send a "GET" request to the "list" endpoint
    Then status code should be 200
    And the response should be match with "get_list_user.json"

  @get-positive
  Scenario: GET: get specific user by ID
    When user send a "GET" request with specific id "60d0fe4f5311236168a109cc"
    Then status code should be 200
    And the response should be match with "get_specific_user_by_id.json"



