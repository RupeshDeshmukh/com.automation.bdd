package com.automation.bdd;

import static org.fest.assertions.api.Assertions.assertThat;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step definition for Search.feature file.
 * 
 * @author Rupesh Deshmukh
 */
public class SearchStepDefinitions {

    private SearchQueryPage searchQueryPage;

    private SearchResultPage searchResultPage;

    private String termEntered = "";

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

        // Hold the information.
        this.termEntered = term;
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
                this.searchResultPage = this.searchQueryPage.pressEnterInQuery();
                break;
            case "search":
            case "google search":
            case "google search button":
            case "search button":
                this.searchResultPage = this.searchQueryPage.clickSearchButton();
                break;
            case "i'm feeling lucky button":
            case "i'm feeling lucky":
            case "lucky":
            case "lucky button":
                this.searchResultPage = this.searchQueryPage.clickLuckyButton();
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

        final String termInTitle = this.searchResultPage.getTermFromTitle();

        assertThat(termInTitle).contains(this.termEntered);
    }

    /**
     * Method to mapping to given statement "I should get result".
     * 
     * @throws Throwable
     */
    @Then("^I should get result \"([^\"]*)\"$")
    public void i_should_get_result_something(String expectedResult) throws Throwable {

        final String result = this.searchResultPage.getResult();

        assertThat(result).contains(expectedResult);
    }
}
