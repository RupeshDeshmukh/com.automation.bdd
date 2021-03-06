/*
 * Copyright (C) 2013, Atos HTTS.
 */
package com.automation.bdd;

import static com.automation.bdd.WebDriverFactory.TIME_OUT_IN_SECONDS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
     */
    public SearchResultPage() {

        this.driver = WebDriverFactory.getWebDriverInstance();

        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIME_OUT_IN_SECONDS), this);

        final WebDriverWait webDriverWait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search li")));
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

    /**
     * Method to get the result.
     * 
     * @return String.
     */
    public String getResult() {

        final WebElement calculatorTextBox = this.driver.findElement(By.id("cwos"));

        return calculatorTextBox.getText();
    }
}
