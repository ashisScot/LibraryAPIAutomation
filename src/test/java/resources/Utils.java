package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public RequestSpecification setRequest() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getValueFromPropertiesFile("baseURI")).setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		}
		return req;
	}
	
	public static String getValueFromPropertiesFile(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/PlaceAPI.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getValueFromResponse(Response response, String key) {
		String responseString = response.asString();
		JsonPath js= new JsonPath(responseString);
		return js.get(key);
		
	}

}
