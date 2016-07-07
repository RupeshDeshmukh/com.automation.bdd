package com.automation.bdd;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Fail.fail;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step definition for Search.feature file.
 * 
 * @author Rupesh Deshmukh
 */
public class SearchStepDefinitions {

    private WebDriver driver;

    private Object currentPage;

    private String termEntered = "";

    /**
     * Method to set-up the web driver before the start of the test.
     */
    @Before({"@requires_browser"})
    public void buildDriver() {
        this.driver = WebDriverFactory.createWebDriver();
    }

    /**
     * Close the driver once the test is finished.
     */
    @After({"@requires_browser"})
    public void destroyDriver() {
        this.driver.quit();
    }

    /**
     * Method to mapping to given statement "A Google search page".
     * 
     * @throws Throwable
     */
    @Given("^A Google search page$")
    public void A_Google_search_page() throws Throwable {

        // Load the driver with google search page.
        this.currentPage = SearchQueryPage.loadUsing(this.driver);
    }

    /**
     * Method to mapping to when statement
     * "I enter the search term \"([^\"]*)\"$".
     * 
     * @throws Throwable
     */
    @When("^I enter the search term \"([^\"]*)\"$")
    public void I_enter_the_search_term(final String term) throws Throwable {

        // Verify whether we are on the correct page or not.
        this.verifyCurrentPage(SearchQueryPage.class);

        // Set the search criteria in google search box.
        ((SearchQueryPage) this.currentPage).setQuery(term);

        // Hold the information.
        this.termEntered = term;
    }

    /**
     * Method to mapping to And statement
     * "I submit the search by pressing \"([^\"]*)\"$".
     * 
     * @throws Throwable
     */
    @And("^I submit the search by pressing \"([^\"]*)\"$")
    public void I_submit_the_search_by_pressing(final String submitType) throws Throwable {

        // Verify whether we are on the correct page or not.
        this.verifyCurrentPage(SearchQueryPage.class);

        switch (submitType.toLowerCase()) {
            case "enter":
            case "enter key":
                this.currentPage = ((SearchQueryPage) this.currentPage).pressEnterInQuery();
                break;
            case "search":
            case "google search":
            case "google search button":
            case "search button":
                this.currentPage = ((SearchQueryPage) this.currentPage).clickSearchButton();
                break;
            case "i'm feeling lucky button":
            case "i'm feeling lucky":
            case "lucky":
            case "lucky button":
                this.currentPage = ((SearchQueryPage) this.currentPage).clickLuckyButton();
                break;
        }
    }

    /**
     * Method to mapping to given statement "A Google search page".
     * 
     * @throws Throwable
     */
    @Then("^The search result page title should contain the search term")
    public void The_search_result_page_title_should_contain_the_word() throws Throwable {

        // Verify whether we are on the correct page or not.
        this.verifyCurrentPage(SearchResultPage.class);

        final String termInTitle = ((SearchResultPage) this.currentPage).getTermFromTitle();

        assertThat(termInTitle).contains(this.termEntered);
    }

    /**
     * Method to verify what is the current page.
     * 
     * @param pageClass
     */
    @SuppressWarnings("rawtypes")
    private void verifyCurrentPage(final Class pageClass) {

        if (!this.currentPage.getClass().equals(pageClass)) {
            fail(String.format("Expected current page to have type %s - actual type is %s", pageClass.getSimpleName(), this.currentPage.getClass()
                            .getSimpleName()));
        }
    }
}
