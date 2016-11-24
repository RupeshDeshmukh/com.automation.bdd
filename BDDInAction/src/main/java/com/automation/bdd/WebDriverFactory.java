/*
 * Copyright: 2013, Atos HTTS.
 */
package com.automation.bdd;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Factory class for generating web driver for the given browser.
 * 
 * @author a120065
 */
public final class WebDriverFactory {

    /**
     * Constant to indicate time out for page load by web driver.
     */
    public static final int TIME_OUT_IN_SECONDS = 60;

    /**
     * Singleton instance of Selenium Web Driver.
     */
    private static WebDriver driver;

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
    public static WebDriver getWebDriverInstance() {

        final String browserName = System.getProperty("browser", "FireFox");

        if (driver == null) {
            switch (browserName) {
                case "Chrome":
                    driver = new ChromeDriver();
                    break;
                case "IE":
                    driver = new InternetExplorerDriver();
                    break;
                case "FireFox":
                default:
                    driver = createFireFoxDriver();
                    break;
            }

            // Set the timeout for the pages.
            driver.manage().timeouts().implicitlyWait(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);

            // Maximise the browser window by default so that the page has a
            // standard layout. Individual tests can resize the browser window
            // if they want to test how the page layout adapts to limited space.
            // This reduces the probability of a test failure caused by an
            // unexpected layout (nested scroll bars, floating menu over links
            // and buttons and so on).
            driver.manage().window().maximize();
        }

        return driver;
    }

    /**
     * Method to create FireFoxDriver instance by setting FirefoxProfile and
     * DesiredCapabilities.
     * 
     * @return <code>org.openqa.selenium.firefox.FirefoxDriver</code>.
     */
    private static WebDriver createFireFoxDriver() {

        final FirefoxProfile firefoxProfile = new FirefoxProfile();

        // Native events may cause tests which open many windows in parallel to
        // be unreliable especially on windows. However, native events work
        // quite well otherwise and
        // are essential for some of the new actions of the Advanced User
        // Interaction. We need native events to be enable especially for
        // testing the WYSIWYG editor.
        firefoxProfile.setEnableNativeEvents(false);

        // Sets whether FireFox should accept SSL certificates which have
        // expired, signed by an unknown authority or are generally untrusted.
        firefoxProfile.setAcceptUntrustedCertificates(true);

        // Disable hardware acceleration in FireFox.
        firefoxProfile.setPreference("layers.acceleration.disabled", true);

        // Make sure FireFox doesn't upgrade automatically on CI agents.
        firefoxProfile.setPreference("app.update.auto", false);

        // Start page configuration to speed up FireFox
        firefoxProfile.setPreference("browser.startup.homepage", "about:blank");
        firefoxProfile.setPreference("pref.browser.homepage.disable_button.bookmark_page", false);
        firefoxProfile.setPreference("pref.browser.homepage.disable_button.restore_default", false);

        final DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

        return new FirefoxDriver(desiredCapabilities);
    }
}
