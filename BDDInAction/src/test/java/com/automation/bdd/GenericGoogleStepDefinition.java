/*
 * Copyright: 2013, Atos HTTS.
 */
package com.automation.bdd;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * Generic step definition for all common steps.
 * 
 * @author a120065
 */
public class GenericGoogleStepDefinition {

    private WebDriver driver;

    private SearchQueryPage searchQueryPage;

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

    /**
     * Method to mapping to when statement "I enter the search term
     * \"([^\"]*)\"$".
     * 
     * @param term
     *            String.
     * @throws Throwable
     */
    @When("^I enter the search term \"([^\"]*)\"$")
    public void I_enter_the_search_term(final String term) throws Throwable {

        this.searchQueryPage = new SearchQueryPage();

        // Set the search criteria in google search box.
        this.searchQueryPage.setQuery(term);
    }

    /**
     * Method to mapping to And statement "I submit the search by pressing
     * \"([^\"]*)\"$".
     * 
     * @param submitType
     *            String.
     * @throws Throwable
     */
    @And("^I submit the search by pressing \"([^\"]*)\"$")
    public void I_submit_the_search_by_pressing(final String submitType) throws Throwable {

        switch (submitType.toLowerCase()) {
            case "enter":
            case "enter key":
                this.searchQueryPage.pressEnterInQuery();
                break;
            case "search":
            case "google search":
            case "google search button":
            case "search button":
                this.searchQueryPage.clickSearchButton();
                break;
            case "i'm feeling lucky button":
            case "i'm feeling lucky":
            case "lucky":
            case "lucky button":
                this.searchQueryPage.clickLuckyButton();
                break;
        }
    }
}
