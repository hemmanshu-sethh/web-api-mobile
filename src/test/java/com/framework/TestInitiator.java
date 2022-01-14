package com.framework;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import com.automation.keywords.TTRHomePage;

public class TestInitiator {

	public static Map<String, String> testEnv = new HashMap<String, String>();
	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	
	public TTRHomePage ttrhomepage;
	public WebDriver getDriver() {
		return this.driver;
	}


	
	
	private void _initPage() {
		ttrhomepage = new TTRHomePage(driver);
	}
	
	
	
	public TestInitiator() {

		this.wdfactory = new WebDriverFactory();	
	}

	public void InitializeBrowser()  throws FileNotFoundException{
		
		ConfigReader config = new ConfigReader();
		testEnv = config.getTestEnv();
		driver = wdfactory.getDriver(testEnv);
		driver.manage().deleteAllCookies();
	}

	public void LaunchApplication() throws FileNotFoundException {
		System.out.println(">>><<<<<<<<<<<<<<<<<<<<<<");

		InitializeBrowser();
		_initPage() ;

		driver.get(testEnv.get("baseURL"));

		
	}
	
	
	public void CloseApplication()
	{
		   driver.close();
	        System.out.println("Finished Test On Chrome Browser");
	}
}
