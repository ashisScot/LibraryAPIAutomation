package parallel;

import java.io.IOException;

import io.cucumber.java.Before;
import resources.Utils;

public class Hooks {
	Utils utils = new Utils();
	@Before("@DELETEPLACE_API")
	public void beforeDeletePlaceScenario() throws IOException {
		StepDefinition stepDef = new StepDefinition();
		stepDef.userProvidesForAddPlacePayload("Hogwarts", "English", "Harry", "6464512354");
		stepDef.userCallsAPI("ADDPlaceAPI", "POST");
		StepDefinition.deletePlace_Id= utils.getValueFromResponse(stepDef.response, "place_id");
		
	}

}
