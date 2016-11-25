/*
 * Copyright: 2013, Atos HTTS.
 */
package com.automation.bdd;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

/**
 * Generic step definition for all common steps.
 * 
 * @author a120065
 */
public class GenericGoogleStepDefinition {

    private WebDriver driver;

    /**
     * Method to set-up the web driver before the start of the test.
     */
    @Before({"@requires_browser"})
    public void buildDriver() {

        this.driver = WebDriverFactory.getWebDriverInstance();
    }

    /**
     * Method to mapping to given statement "A Google search page".
     * 
     * @throws Throwable
     */
    @Given("^A Google search page$")
    public void A_Google_search_page() throws Throwable {

        // Load the driver with google search page.
        SearchQueryPage.loadPage(this.driver);
    }
}
