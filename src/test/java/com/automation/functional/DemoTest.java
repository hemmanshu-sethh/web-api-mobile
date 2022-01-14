package com.automation.functional;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import  com.framework.TestInitiator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoTest {
    private static Logger logger = LogManager.getLogger();
    TestInitiator test = new TestInitiator();
    @Test
    
    
    public void f() throws IOException {
    
        String testTitle = "Home - Two Tyred Riders";
        String originalTitle = "Home - Two Tyred Riders";
        test.LaunchApplication();
        Assert.assertEquals(originalTitle, testTitle);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Starting Test On Chrome Browser");
    }

    @AfterMethod
    public void afterMethod() {
     
    }

   

    
}
