package com.automation.bdd;

import static org.fest.assertions.api.Assertions.assertThat;
import cucumber.api.java.en.Then;

/**
 * Step definition for Calculator.feature file.
 * 
 * @author a120065
 */
public class CalculatorStepDefinitions {

    /**
     * Method to mapping to given statement "I should get result".
     * 
     * @throws Throwable
     */
    @Then("^I should get result \"([^\"]*)\"$")
    public void I_should_get_result_something(final String expectedResult) throws Throwable {

        final SearchResultPage searchResultPage = new SearchResultPage();

        final String result = searchResultPage.getResult();

        assertThat(result).contains(expectedResult);
    }
}
