package com.framework.getpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseUI {


	protected WebDriver driver;
	public SeleniumWait wait;
	private String pageName;


	protected BaseUI (WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, 10);

	}
	
	protected String getPageTitle() {
		return driver.getTitle();
	}
}
