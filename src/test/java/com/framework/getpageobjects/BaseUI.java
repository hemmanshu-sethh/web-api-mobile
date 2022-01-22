package com.framework.getpageobjects;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BaseUI {


	protected WebDriver driver;
	public SeleniumWait wait;
	private String PageName;


	protected BaseUI (WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.PageName = pageName;
		this.wait = new SeleniumWait(driver, 10);

	}
	
	protected String getPageTitle() {
		return driver.getTitle();
	}
	
	protected void click(WebElement element) {
		try {
			wait.waitForElementToBeVisible(element);
//			scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
//			scrollDown(element);
			element.click();
//			logMessage("Clicked Element " + element
//					+ " after catching Stale Element Exception");
		} catch (Exception ex2) {
//			logMessage("Element " + element + " could not be clicked! "
//					+ ex2.getMessage());
		}
	}
	
}
