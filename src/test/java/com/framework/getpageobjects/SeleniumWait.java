package com.framework.getpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWait {
	   WebDriver driver;
	    WebDriverWait wait;
	    
	    int timeout;
	    
	    public SeleniumWait(WebDriver driver, int timeout) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, timeout);
	        this.timeout = timeout;
	    }
	    
	    public WebElement waitForElementToBeVisible(WebElement element) {
	        return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
	    }

}
