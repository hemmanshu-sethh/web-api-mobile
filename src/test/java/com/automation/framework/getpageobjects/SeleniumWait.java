package com.automation.framework.getpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }
	    
	    public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
	        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	    }
	    
	    public WebElement waitForElementToBeClickable(WebElement element) {
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

}
