package com.automation.functional;


import com.framework.configReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.framework.configReader.testEnv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoTest {
    private static Logger logger = LogManager.getLogger();

    WebDriver driver ;
    @Test
    public void f() throws IOException {
    	
        configReader configReader = new configReader();
        configReader.getTestEnv();
        
        System.out.println("Launching Google Chrome browser");
        driver = new FirefoxDriver();
        driver.get(testEnv.get("baseURL"));
        String testTitle = "Home - Two Tyred Riders";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(originalTitle, testTitle);
        logger.debug("It is a debug logger2.");


    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Starting Test On Chrome Browser");
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
        System.out.println("Finished Test On Chrome Browser");
    }

    public static void chromeVersion() throws IOException {

        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec("reg query " + "HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon " +  "/v version");
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        // Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }

    }
}
