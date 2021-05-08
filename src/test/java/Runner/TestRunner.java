package Runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features", glue = { "stepDefinations" }, plugin = { "pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class TestRunner extends AbstractTestNGCucumberTests  {
//, tags ="@DELETEPLACE_API"
}
