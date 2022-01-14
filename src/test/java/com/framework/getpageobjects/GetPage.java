package com.framework.getpageobjects;

import org.openqa.selenium.WebDriver;

public class GetPage extends BaseUI {

    protected WebDriver webdriver;
    String pageName;

    //Constructor
    public GetPage(WebDriver driver, String pageName) {
        super(driver, pageName);
        this.webdriver = driver;
        this.pageName = pageName;
    }
}
