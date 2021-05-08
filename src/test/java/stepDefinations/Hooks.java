package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;
import resources.Utils;

public class Hooks {
	Utils utils = new Utils();
	@Before("@DELETEPLACE_API")
	public void beforeDeletePlaceScenario() throws IOException {
		if(StepDefinition.place_Id == null) {
		StepDefinition stepDef = new StepDefinition();
		stepDef.userProvidesForAddPlacePayload("Hogwarts", "English", "Harry", "6464512354");
		stepDef.userCallsAPI("ADDPlaceAPI", "POST");
		StepDefinition.place_Id = utils.getValueFromResponse(stepDef.response, "place_id");
		}
	}

}
