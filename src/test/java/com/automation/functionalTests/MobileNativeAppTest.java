package com.automation.functionalTests;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.framework.TestInitiator;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
;


public class MobileNativeAppTest {
	private static Logger logger = LogManager.getLogger();
	TestInitiator test;
	String app_url;


	@BeforeClass
	public void setupTest() throws FileNotFoundException {
		test = new TestInitiator("Test Name");		
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Launching Application");
	}

	@Test(description = "Test to Verify Google Home Page Launch")
	public void testGoogleHomePageLaunch() throws IOException, Exception {
		test.calculator.AdditionTest();
		
	}
	
	
	  @AfterClass
	    public void testEnd() throws InterruptedException {
	        test.closeTestSession();
	    }

}