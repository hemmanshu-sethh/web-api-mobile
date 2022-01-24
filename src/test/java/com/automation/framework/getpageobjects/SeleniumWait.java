package com.automation.framework.getpageobjects;

import java.util.List;

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
	    
	    public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
	        return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	    }
	    
	    public WebElement waitForElementToBeClickable(WebElement element) {
	        return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

}
