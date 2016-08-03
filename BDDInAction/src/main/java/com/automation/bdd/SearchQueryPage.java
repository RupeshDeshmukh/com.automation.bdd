package com.automation.bdd;

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
    public SearchQueryPage(final WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    /**
     * Method to load the google search page.
     * 
     * @param driver
     *            <code>org.openqa.selenium.WebDriver</code>.
     * @return <code>net.wl.selenium.SearchQueryPage</code>.
     */
    public static SearchQueryPage loadUsing(final WebDriver driver) {

        // Navigate to google page.
        driver.get("https://www.google.co.in/");

        return new SearchQueryPage(driver);
    }

    /**
     * Method to clear and set the search value for the google search field.
     * 
     * @param term
     *            String to be searched.
     * @return <code>net.wl.selenium.SearchQueryPage</code>.
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
     * @return <code>net.wl.selenium.SearchResultPage</code>.
     */
    public SearchResultPage pressEnterInQuery() {

        this.query.sendKeys("\n");

        return new SearchResultPage(this.driver);
    }

    /**
     * Method to click the "Google Search" button on google search page.
     * 
     * @return <code>net.wl.selenium.SearchResultPage</code>.
     */
    public SearchResultPage clickSearchButton() {

        this.searchButton.click();

        return new SearchResultPage(this.driver);
    }

    /**
     * Method to click the "I'm Feeling Lucky" button on google search page.
     * 
     * @return <code>net.wl.selenium.SearchResultPage</code>.
     */
    public SearchResultPage clickLuckyButton() {

        this.luckyButton.click();

        return new SearchResultPage(this.driver);
    }
}
