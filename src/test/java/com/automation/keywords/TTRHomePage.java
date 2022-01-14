package com.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.framework.getpageobjects.GetPage;

public class TTRHomePage extends GetPage {
	
	public TTRHomePage(WebDriver driver) {
		super(driver, "TTRHomePage");
	}

	public void verifyuserisoncampusloginpage() {
		Assert.assertEquals(driver.getTitle(), getPageTitle());
	}

}
