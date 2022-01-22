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
		InitializeBrowser();
		_initPage();
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
		System.out.println(config.getTestEnv().toString());
		driver = wdfactory.getDriver(testEnv);
		System.out.println("000000"+driver);

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
