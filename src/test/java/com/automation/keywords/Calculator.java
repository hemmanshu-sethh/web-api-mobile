package com.automation.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.automation.framework.getpageobjects.GetPage;

public class Calculator extends GetPage {

	
	public Calculator(WebDriver driver) {
		super(driver, "Calculator");
	}
	
	public void pressKey(String key) throws InterruptedException {
				 element(key).click();
	}

	public String getResult() throws InterruptedException {

		pressKey("equalto");
		return element("screen").getText();
	}
}
