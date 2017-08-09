package com.automation.bdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * BDD Basic example using Cucumber and Selenium.
 * 
 * <ul>
 * <li>Refer to <code>com.automation.bdd.SearchStepDefinitions</code> for step
 * definitions.</li>
 * <li>Refer to <code>com.automation.bdd.Search.feature</code> for related
 * feature file.</li>
 * </ul>
 * 
 * @author Rupesh Deshmukh
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/html"}, monochrome = false, features = {
        "src/test/resources/com/automation/bdd"})
public class BDDBasicExampleTest {
}
