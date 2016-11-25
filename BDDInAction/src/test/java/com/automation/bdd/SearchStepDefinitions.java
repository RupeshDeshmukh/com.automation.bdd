package com.automation.bdd;

import static org.fest.assertions.api.Assertions.assertThat;
import cucumber.api.java.en.Then;

/**
 * Step definition for Search.feature file.
 * 
 * @author Rupesh Deshmukh
 */
public class SearchStepDefinitions {

    /**
     * Method to mapping to given statement "A Google search page".
     * 
     * @throws Throwable
     */
    @Then("^The search result page title should contain the \"([^\"]*)\"$")
    public void The_search_result_page_title_should_contain_the_word(final String searchTerm) throws Throwable {

        final SearchResultPage searchResultPage = new SearchResultPage();

        final String termInTitle = searchResultPage.getTermFromTitle();

        assertThat(termInTitle).contains(searchTerm);
    }
}
