package resources;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporting extends TestListenerAdapter{
	
	ExtentReports extent;
	ExtentTest test;
	ExtentSparkReporter spark;
	
public void onStart(ITestContext testContext) {
	// TODO Auto-generated method stub
	String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
	String reportName = "LibraryReportAPI_"+timeStamp+".html";
	extent = new ExtentReports();
	 spark = new ExtentSparkReporter("./test-output/"+reportName);
	 
	 try {
		spark.loadXMLConfig("./extent-config.xml");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 extent.attachReporter(spark);
	 extent.setSystemInfo("Host name", "localHost");
	 extent.setSystemInfo("environment", "QA");
	 extent.setSystemInfo("user", "Ashis");
}

//@Override
//	public void onTestSuccess(ITestResult tr) {
//		// TODO Auto-generated method stub
//		//test = extent.createTest
//	}
//	
//@Override
//	public void onTestFailure(ITestResult tr) {
//		// TODO Auto-generated method stub
//		super.onTestFailure(tr);
//	}
//
//@Override
//	public void onTestSkipped(ITestResult tr) {
//		// TODO Auto-generated method stub
//		super.onTestSkipped(tr);
//	}
//
@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		extent.flush();
	}
}
