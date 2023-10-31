@api
  Feature: POST

    Background:
      Given all request header is properly setup

      # NOTES
      # requred fields is only first name, last name and email
      @post-positive
      Scenario: POST: create new user with required field only
        And user prepare "required fields" body for "POST" method
        When user send a "POST" request to the "create" endpoint
        Then status code should be 200
        And the response should be match with "post_required_fields.json"
        And the response body "id" will be named as "reusableID_01" and will be used for next test
