Feature: Validating Place API's
@AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload test with "<name>" "<language>" "<address>"
    When user calls "addPaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_ID created maps to "<name>" using "getPlaceAPI"

    Examples:
  |name|language|address|
  |Art|English|Niko|
  |Art1|French|Niko2|

@DeletePlace
Scenario: Verify if DeletePlace Functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"