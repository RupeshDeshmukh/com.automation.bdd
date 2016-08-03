/*
 * Copyright: 2013, Atos HTTS.
 */
package com.automation.bdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Factory class for generating web driver for the given browser.
 * 
 * @author a120065
 */
public final class WebDriverFactory {

    /**
     * Constant to indicate time out for page load by web driver.
     */
    public static final int TIME_OUT_IN_SECONDS = 30;

    /**
     * Utility class should not have default or public constructor.
     */
    private WebDriverFactory() {
        throw new UnsupportedOperationException();
    }

    /**
     * Method to create web driver based on the browser name passed.
     * 
     * @return <code>org.openqa.selenium.WebDriver</code>.
     */
    public static WebDriver createWebDriver() {

        final String browserName = System.getProperty("browser", "FireFox");

        switch (browserName) {
            case "FireFox":
                return new FirefoxDriver();
            case "Chrome":
                return new ChromeDriver();
            default:
                throw new UnsupportedOperationException("Unsupported browser type : " + browserName);
        }
    }
}
