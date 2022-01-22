package com.automation.keywords;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.framework.ConfigReader;

import com.framework.getpageobjects.GetPage;
import com.framework.ConfigReader;

public class GoogleHomePage extends GetPage {	
	
	public GoogleHomePage(WebDriver driver) {
		super(driver,"GoogleHomePage" );
	}
	
	ConfigReader config = new ConfigReader();


	public void verifyHomePageTitle() throws IOException {
		
		Assert.assertEquals(this.driver.getTitle(),config.getPageTitle("TTRHomePage"));
	}


	public String  getTestData(String ElementName)
	{
		return getTestDataFromYaml(ElementName);
	}
	public void PerformSearch() throws NoSuchElementException, IOException, InterruptedException {
		 
				WebElement elm = element("searchbox");
				elm.click();
				elm.sendKeys(getTestData("searchbox"));
				elm.sendKeys(Keys.ENTER);	
	}


	public void clickFirstResult() throws InterruptedException {
WebElement elm = element("firstresult");

try {
    elm.click();
}
catch(org.openqa.selenium.StaleElementReferenceException ex)
{
    elm.click();
}
	 	 

	}
}
