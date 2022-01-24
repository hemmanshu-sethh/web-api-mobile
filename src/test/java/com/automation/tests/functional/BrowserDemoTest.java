package com.automation.tests.functional;

import com.automation.framework.TestInitiator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class BrowserDemoTest {
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

	@Test(description = "Test to Verify Google Home Page Launch")
	public void testGoogleHomePageLaunch() throws Exception {
		test.LaunchApplication();
	}
	
	@Test(dependsOnMethods="testGoogleHomePageLaunch",description = "Test to Verify Google Home Page Launch")
	public void performGoogleSearch() throws Exception {
		test.googleHomePage.PerformSearch();
	}
	
	@Test(dependsOnMethods = "performGoogleSearch",description = "Test to Validate Books Selection")
	public void googleBooksTab() throws Exception {
		test.googleHomePage.clickBooks();
		test.googleHomePage.validateBooksFilters();
	}
	

    @AfterClass
    public void testEnd() throws InterruptedException {
        test.closeTestSession();
    }


}
