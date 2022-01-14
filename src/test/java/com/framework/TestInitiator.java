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

public class TestInitiator {

	public static Map<String, String> testEnv = new HashMap<String, String>();
	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	
	public TestInitiator() {

		this.wdfactory = new WebDriverFactory();	
	}

	public void InitializeBrowser()  throws FileNotFoundException{
		
		ConfigReader config = new ConfigReader();
		testEnv = config.getTestEnv();
		System.out.println(">>>"+testEnv.get("browser"));
		driver = wdfactory.getDriver(testEnv);
		driver.manage().deleteAllCookies();
	}

	public void LaunchApplication() throws FileNotFoundException {
		
		InitializeBrowser();
		driver.get(testEnv.get("baseURL"));
		
	}
	
	
	public void CloseApplication()
	{
		   driver.close();
	        System.out.println("Finished Test On Chrome Browser");
	}
}
