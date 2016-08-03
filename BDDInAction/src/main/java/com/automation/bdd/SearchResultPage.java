/*
 * Copyright: 2013, Atos HTTS.
 */
package com.automation.bdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class to manage search result page.
 * 
 * @author Rupesh Deshmukh
 */
public class SearchResultPage {

    /**
     * Slf4j logger instance for logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchResultPage.class);

    private final WebDriver driver;

    /**
     * Constructor to initialise the web driver for the search result page.
     * 
     * @param driver
     *            <code>org.openqa.selenium.WebDriver</code>.
     */
    public SearchResultPage(final WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);

        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search li")));
    }

    /**
     * Method to get search result page title.
     * 
     * @return String page title.
     */
    public String getTermFromTitle() {

        final String title = this.driver.getTitle();

        LOGGER.info(title);

        return title.substring(0, title.indexOf(" - "));
    }
}
