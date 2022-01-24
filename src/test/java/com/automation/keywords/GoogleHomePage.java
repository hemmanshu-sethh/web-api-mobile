package com.automation.keywords;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.automation.framework.ConfigReader;

import com.automation.framework.getpageobjects.GetPage;

public class GoogleHomePage extends GetPage {

	public GoogleHomePage(WebDriver driver) {
		super(driver, "GoogleHomePage");
	}

	ConfigReader config = new ConfigReader();

	public void verifyHomePageTitle() throws IOException {

		Assert.assertEquals(this.driver.getTitle(), config.getPageTitle("TTRHomePage"));
	}

	public String getTestData(String ElementName) {
		return getTestDataFromYaml(ElementName);
	}

	public void PerformSearch() throws NoSuchElementException, IOException, InterruptedException {

		WebElement elm = element("searchbox");
		elm.click();
		elm.sendKeys(getTestData("searchbox"));
		elm.sendKeys(Keys.ENTER);
	}

	public void clickBooks() throws InterruptedException {
		WebElement elm = element("books");
		elm.click();

	}

	public void validateBooksFilters() throws InterruptedException {
		WebElement elm = element("DocumentFilter");
		Assert.assertEquals( elementLabel("DocumentFilter"),elm.getText());
	}

}
