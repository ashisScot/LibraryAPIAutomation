package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pojo.AddPlacePojo;
import pojo.Location;

public class TestDataBuilder {
	
	public AddPlacePojo addPlacePayloadAPI(String address , String language, String name,String phoneNumber) {
		AddPlacePojo addPlace = new AddPlacePojo();
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlace.setLocation(location);
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setWebsite("http://google.com");
		addPlace.setName(name);
		addPlace.setPhone_number(phoneNumber);
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		addPlace.setTypes(typesList);
		return addPlace;
		
	}
	
	public String deletePlacePayload(String placeId) {
		String deletePlacePayLoadString = "{\r\n" + 
				"  \"place_id\":\""+placeId+"\"\r\n" + 
				"}"	;
		return deletePlacePayLoadString;
				}
	

}
