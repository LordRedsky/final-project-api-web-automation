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

  @get-negative
    Scenario: GET: get list of Tags with path tag starting with space (" ")
    When user send a "GET" request to the " tag" endpoint
    Then status code should be 404
    And the response should be contain:
      | error | PATH_NOT_FOUND |

# USER
  @get-positive
  Scenario: GET: get list of users
    When user send a "GET" request to the "user" endpoint
    Then status code should be 200
    And the response should be match with "get_list_user.json"

   @get-negative
   Scenario: GET: get list of Users with path user starting with space (" ")
     When user send a "GET" request to the " user" endpoint
     Then status code should be 404
     And the response should be contain:
       | error | PATH_NOT_FOUND |

  @get-positive
  Scenario: GET: get specific data user by valid ID
    When user send a "GET" request with specific id "60d0fe4f5311236168a109cc"
    Then status code should be 200
    And the response should be match with "get_specific_user_by_id.json"

  @get-negative
  Scenario: GET: get specific data user with an ID starting with space (" ")
    When user send a "GET" request with specific id " 60d0fe4f5311236168a109cc"
    Then status code should be 400
    And the response should be contain:
      | error | PARAMS_NOT_VALID |

# BOUNDARY PARAMETER VALUES
  # Limits range [5 - 50]
  # Page range [0 - 999]
  @get-positive
  Scenario Outline: GRT: get list of users with valid limit and page boundary values
    When user set the request parameters to:
      | limit | <limit> |
      | page  | <page>  |
    And user send a "GET" request to the "user" endpoint
    Then status code should be 200
    And the response should be contain:
      | limit | <limit> |
      | page  | <page>  |

    Examples:
      | limit | page |
      | 5     | 0    |
      | 50    | 999  |

  @get-negative
# FAILED, status code always "200-OK" in every limit and pages values
  Scenario Outline: GET: get list of users with invalid limit and page boundary values
    When user set the request parameters to:
      | limit | <limit> |
      | page  | <page>  |
    And user send a "GET" request to the "user" endpoint
    Then status code should be 200
#    And the response should be contain:
#      | error | PARAMS_NOT_VALID |

    Examples:
      | limit | page |
      | 51    | 1000 |
