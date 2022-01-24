package com.automation.framework;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.automation.framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

import com.automation.keywords.GoogleHomePage;
import com.automation.keywords.Calculator;


public class TestInitiator {

	public static Map<String, String> testEnv = new HashMap<String, String>();
	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	public GoogleHomePage googleHomePage;
	public Calculator calculator;
	
		
	public TestInitiator(String testname) throws FileNotFoundException {
		wdfactory = new WebDriverFactory();
		initializeTestEnvironment();
		_initPage();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}


	
	
	private void _initPage() {
		googleHomePage = new GoogleHomePage(driver);
		calculator = new Calculator(driver);
		
	}
	
	

	public void initializeTestEnvironment()  throws FileNotFoundException{
		
		ConfigReader config = new ConfigReader();
		testEnv = config.getTestEnv();
		driver = wdfactory.getDriver(testEnv);

//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait((60), TimeUnit.SECONDS);
		
	}
	
	

	public void LaunchApplication() throws FileNotFoundException, InterruptedException {
		

		driver.get(testEnv.get("baseURL"));
	}
	
	


	public void closeBrowserSession() throws InterruptedException {
		System.out.println("\n");
        Thread.sleep(100);
		driver.quit();
	}
	
	public void closeTestSession() throws InterruptedException {
		closeBrowserSession();
		
	}



}
