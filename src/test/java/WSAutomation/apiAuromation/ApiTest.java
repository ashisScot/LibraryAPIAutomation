package WSAutomation.apiAuromation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApiTest {
	
	public static void main(String[] args) throws IOException {
		String baseURL = "https://rahulshettyacademy.com";
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String placeId="";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("Payload1.json")))).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsonPath = new JsonPath(response);
		placeId = jsonPath.getString("place_id");
		System.out.println(placeId);
		String newAddress = "New Jersey";
		
		String updateResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).header("Content-Type","application/json").body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath updateJson = new JsonPath(updateResponse);
		
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200);
		

		
	}
	
	
	
}
