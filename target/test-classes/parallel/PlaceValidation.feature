Feature: Add Delete Place API

@VerifyAddPlaceAPI
Scenario Outline: Verify Add Place API
Given user provides for Add Place Payload for '<Address>' '<Language>' '<name>' '<phoneNum>'
And User calls 'ADDPlaceAPI' with 'POST' request
And API calls got success with status code 200
Then Verify placeId created maps to '<name>' using 'GetPlaceAPI'

Examples:
|Address|Language|name|phoneNum|
|29, side layout, cohen 09|French|PSG|8562146585|
|London|English|Hogwarts|9456128754|
|Madrid|Spanish|Santiago Bernabue|9456128754|


@DELETEPLACE_API
Scenario: Verify Delete Place API
Given user provides Delete place payload
And User calls 'DeletePlaceAPI' with 'POST' request
Then API calls got success with status code 200
