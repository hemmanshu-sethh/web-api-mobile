package com.framework;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.automation.keywords.GoogleHomePage;

public class TestInitiator {

	public static Map<String, String> testEnv = new HashMap<String, String>();
	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	public GoogleHomePage ttrhomepage;
	
		
	public TestInitiator(String testname) throws FileNotFoundException {
		wdfactory = new WebDriverFactory();
	}

	
	public WebDriver getDriver() {
		return this.driver;
	}


	
	
	private void _initPage() {
		ttrhomepage = new GoogleHomePage(driver);

	}
	
	

	public void InitializeBrowser()  throws FileNotFoundException{
		
		ConfigReader config = new ConfigReader();
		testEnv = config.getTestEnv();

		driver = wdfactory.getDriver(testEnv);
		System.out.println("000000"+driver);

		driver.manage().deleteAllCookies();

		
	}

	public void LaunchApplication() throws FileNotFoundException, InterruptedException {
		
		InitializeBrowser();
		_initPage() ;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(testEnv.get("baseURL"));
        Thread.sleep(500);
		  driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		  
	}
	
	
	public void CloseApplication()
	{
//		   driver.close();
//	        System.out.println("Finished Test On Chrome Browser");
	}
}
