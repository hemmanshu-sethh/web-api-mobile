package com.automation.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.automation.framework.getpageobjects.GetPage;

public class Calculator extends GetPage {

	
	public Calculator(WebDriver driver) {
		super(driver, "GoogleHomePage");
	}
	
	public void AdditionTest()
	{
		System.out.println("Calculate sum of two numbers");
		//Locate elements using By.name() to enter data and click +/= buttons
		driver.findElement(By.id("com.google.android.calculator:id/digit_1")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
		driver.findElement(By.id("com.google.android.calculator:id/eq")).click();
		// Get the result text
		WebElement sumOfNumbersEle = driver.findElement(By.className("android.widget.TextView"));
		String sumOfNumbers = sumOfNumbersEle.getText();
		
		//verify if result is 3
		Assert.assertTrue(sumOfNumbers.endsWith("3"));
	}
}
