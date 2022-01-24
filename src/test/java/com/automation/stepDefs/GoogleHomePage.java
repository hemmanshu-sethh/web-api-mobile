package com.automation.stepDefs;

import com.automation.framework.TestInitiator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GoogleHomePage {
    TestInitiator test;
    String app_url;

    @Before
    public void setupTest() throws FileNotFoundException {
        test = new TestInitiator("Google Home Page Search");
    }

    @Given("Google Home Page is Launched")
    public void google_Home_Page_is_Launched() throws FileNotFoundException, InterruptedException {
        test.LaunchApplication();;

    }

    @When("I perform Search")
    public void i_perform_Search() throws IOException, InterruptedException {
        test.googleHomePage.PerformSearch();
    }

    @When("I click on Books")
    public void i_click_on_Books() throws InterruptedException {
        test.googleHomePage.clickBooks();

    }

    @Then("I am navigated to Books Result Page")
    public void i_am_navigated_to_Books_Result_Page() throws InterruptedException {
        test.googleHomePage.validateBooksFilters();

    }

    @After
    public void testEnd() throws InterruptedException {
        test.closeTestSession();
    }

}
