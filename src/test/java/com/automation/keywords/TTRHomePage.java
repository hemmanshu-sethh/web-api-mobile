package com.automation.keywords;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.framework.ConfigReader;

import com.framework.getpageobjects.GetPage;
import com.framework.ConfigReader;

public class TTRHomePage extends GetPage {	
	public TTRHomePage(WebDriver driver) {
		super(driver, "TTRHomePage");
	}
	
	ConfigReader config = new ConfigReader();


	public void verifyHomePageTitle() throws IOException {
		
		Assert.assertEquals(this.driver.getTitle(),config.getPageTitle("TTRHomePage"));
	}


	public void clickOnSignOn() throws NoSuchElementException, IOException {
		 
				WebElement test = element("TTRHomePage","searchbox","windows");
				 test.click();
				 test.sendKeys("test");
				 test.sendKeys(Keys.ENTER);	
		
//		  element("searchbox").click();
//		 
		 
//	      WebElement p=driver.findElement(By.name("q"));
//	      p.sendKeys("Selenium Java");
//	      WebDriverWait w = new WebDriverWait(driver, 5);
//	      w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
//	      p.submit();

//		 
//		  WebDriverWait w = new WebDriverWait(driver, 5);
//      w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
//		 //	      WebElement p=driver.findElement(By.name("q"));

	}



}
