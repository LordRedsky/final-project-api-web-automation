@api
Feature: DELETE

  Background:
    Given all request header is properly setup

  @delete-positive
  Scenario Outline: DELETE: delete data with valid ID user
    When user send a "DELETE" request with id "<validID>"
    Then status code should be 200
    And the response should be match with "delete_user_by_id.json"

    Examples:
      | validID     |
      | ID_required |
      | ID_full     |

