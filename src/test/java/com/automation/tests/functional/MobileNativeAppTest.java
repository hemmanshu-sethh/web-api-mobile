package com.automation.tests.functional;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.framework.TestInitiator;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MobileNativeAppTest {
	private static final Logger logger = LogManager.getLogger();
	TestInitiator test;
	String app_url;


	@BeforeClass
	public void setupTest() throws FileNotFoundException {
		test = new TestInitiator("Test Name");		
	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@Test(description = "Test to Verify Addition")
	public void validateAddition() throws Exception {
		test.calculator.pressKey("1");
		test.calculator.pressKey("9");
		test.calculator.pressKey("add");
		test.calculator.pressKey("1");
		test.calculator.pressKey("1");


		Assert.assertEquals(test.calculator.getResult(),"30");


	}
	
	
	  @AfterClass
	    public void testEnd() throws InterruptedException {
	        test.closeTestSession();
	    }

}