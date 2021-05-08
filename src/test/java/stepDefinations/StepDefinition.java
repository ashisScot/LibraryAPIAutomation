package stepDefinations;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

public class StepDefinition extends Utils {
	
	RequestSpecification req;
	ResponseSpecification respnseSpec;
	Response response;
	TestDataBuilder testData = new TestDataBuilder();
	static String place_Id ;
	
	@Given("user provides for Add Place Payload for {string} {string} {string} {string}")
	public void userProvidesForAddPlacePayload(String address, String language, String name, String phoneNumber)
			throws IOException {
		req = given().spec(setRequest()).body(testData.addPlacePayloadAPI(address, language, name, phoneNumber));
	}
	
	@And("^User calls '(.*)' with '(.*)' request$")
	public void userCallsAPI(String apiName, String requestType) {
		APIResources apiResource = APIResources.valueOf(apiName);
		
		respnseSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		if(requestType.equals("GET")) {
			response = req.when().get(apiResource.getResource()).then().spec(respnseSpec).extract().response();
		}
		else if (requestType.equals("POST")) {
			response = req.when().post(apiResource.getResource()).then().spec(respnseSpec).extract().response();
		}
		
	}
	
	@And("API calls got success with status code {int}")
	public void verifyStatusCode(Integer statuscode) {
		int actualStatusCode = response.getStatusCode();
		assertTrue("Status Code is not correct "+statuscode, actualStatusCode == statuscode);

	}
	
	@Then("^Verify placeId created maps to '(.*)' using '(.*)'$")
	public void verifyPlaceIdCreatedCorrectly(String name, String apiName) throws IOException {
		place_Id = getValueFromResponse(response, "place_id");
		req = given().spec(setRequest()).queryParam("place_id", place_Id);
		userCallsAPI(apiName, "GET");
		String placeName = getValueFromResponse(response, "name");
		assertTrue("Correct Place is not added", placeName.equals(name));
	}
	
	@Given("^user provides Delete place payload$")
	public void userProvidesDeletePlacePayload()throws IOException {
		req = given().spec(setRequest()).body(testData.deletePlacePayload(place_Id));
		
	}


	
	

}
