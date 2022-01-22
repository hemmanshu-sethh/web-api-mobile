package com.automation.functional;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import  com.framework.TestInitiator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.framework.ConfigReader.getElementsTestData;;


public class DemoTest {
    private static Logger logger = LogManager.getLogger();
    TestInitiator test;
    String app_url;
    
    @BeforeClass
    public void setupTest() throws FileNotFoundException {
         test = new TestInitiator("Test Name");
    }
        
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Starting Test On Chrome Browser");
    }

    @Test(description = "Test to Verify Google Home Page Launch")
    public void testGoogleHomePageLaunch() throws IOException, Exception {
    	test.LaunchApplication();
       test.ttrhomepage.PerformSearch();
        }

    @Test(dependsOnMethods = "testGoogleHomePageLaunch", description = "Perform Search")
    public void stravaSignIn() {
      
    	
    }
    
    
    @AfterMethod
    public void afterMethod() {
//    	test.CloseApplication();
     
    }

   

    
}
