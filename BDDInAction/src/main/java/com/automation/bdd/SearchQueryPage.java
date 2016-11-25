/*
 * Copyright: 2013, Atos HTTS.
 */
package com.automation.bdd;

import static com.automation.bdd.WebDriverFactory.TIME_OUT_IN_SECONDS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Helper class to manage search page.
 * 
 * @author Rupesh Deshmukh
 */
public class SearchQueryPage {

    private final WebDriver driver;

    @FindBy(css = "input[name=q]")
    private WebElement query;

    @FindBy(css = "input[value=\"Google Search\"]")
    private WebElement searchButton;

    @FindBy(css = "input[value=\"I'm Feeling Lucky\"]")
    private WebElement luckyButton;

    /**
     * Constructor to load the web driver for the google search page.
     * 
     * @param driver
     *            <code>org.openqa.selenium.WebDriver</code>.
     */
    public SearchQueryPage() {

        this.driver = WebDriverFactory.getWebDriverInstance();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIME_OUT_IN_SECONDS), this);
    }

    /**
     * Method to load the google search page.
     * 
     * @param driver
     *            <code>org.openqa.selenium.WebDriver</code>.
     */
    public static void loadPage(final WebDriver driver) {

        // Navigate to google page.
        driver.get("https://www.google.co.in/");
    }

    /**
     * Method to clear and set the search value for the google search field.
     * 
     * @param term
     *            String to be searched.
     * @return <code>com.automation.bdd.SearchQueryPage</code>.
     */
    public SearchQueryPage setQuery(final String term) {

        // Clear the search criteria field.
        this.query.clear();

        this.query.sendKeys(term);

        return this;
    }

    /**
     * Method to send enter key in the search.
     * 
     * @return <code>com.automation.bdd.SearchResultPage</code>.
     */
    public SearchResultPage pressEnterInQuery() {

        this.query.sendKeys("\n");

        return new SearchResultPage();
    }

    /**
     * Method to click the "Google Search" button on google search page.
     * 
     * @return <code>com.automation.bdd.SearchResultPage</code>.
     */
    public SearchResultPage clickSearchButton() {

        this.searchButton.click();

        return new SearchResultPage();
    }

    /**
     * Method to click the "I'm Feeling Lucky" button on google search page.
     * 
     * @return <code>com.automation.bdd.SearchResultPage</code>.
     */
    public SearchResultPage clickLuckyButton() {

        this.luckyButton.click();

        return new SearchResultPage();
    }
}
