/*
 * Copyright: 2016, Worldline UK&I.
 */
package com.automation.bdd;

import static com.automation.bdd.utils.SeleniumDriverProperties.CHROME_DRIVER_SYSTEM_PROPERTY_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.CHROME_DRIVER_WINDOWS_FILENAME_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.CHROME_DRIVER_LINUX_FILENAME_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.CHROME_DRIVER_MAC_FILENAME_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.GECKO_DRIVER_SYSTEM_PROPERTY_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.GECKO_DRIVER_WINDOWS_FILENAME_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.GECKO_DRIVER_LINUX_FILENAME_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.GECKO_DRIVER_MAC_FILENAME_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.IE_DRIVER_SYSTEM_PROPERTY_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.IE_DRIVER_WINDOWS_FILENAME_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.SELENIUM_DRIVERS_BASE_PATH_KEY;
import static com.automation.bdd.utils.SeleniumDriverProperties.getSeleniumDriverProperty;
import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.openqa.selenium.remote.BrowserType.IEXPLORE;
import static org.openqa.selenium.remote.BrowserType.SAFARI;

import java.awt.Toolkit;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.bdd.utils.SeleniumDriverProperties;

/**
 * Factory class for generating web driver for the given browser.
 *
 * Created by a120065, in04468 on 25-11-2016.
 */
public final class WebDriverFactory implements Serializable {

    /**
     * Generated serial version Id.
     */
    private static final long serialVersionUID = 8337223059281231033L;

    /**
     * Logger instance for doing logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);

    /**
     * Constant to indicate time out for page load by web driver.
     */
    public static final int TIME_OUT_IN_SECONDS = 15;

    /**
     * Singleton instance of Selenium Web Driver.
     */
    private static WebDriver webDriver;

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
    public synchronized static WebDriver getWebDriverInstance() {

        if (webDriver == null) {

            // Load the selenium driver properties.
            SeleniumDriverProperties.loadSeleniumDriverProperties();

            try {
                webDriver = createBrowserSpecificDriver();
            } catch (final UnreachableBrowserException e) {
                webDriver = createBrowserSpecificDriver();
            } catch (final WebDriverException e) {
                webDriver = createBrowserSpecificDriver();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
        }

        return webDriver;
    }

    /**
     * Method to get the web driver instance based on the asked browser.
     *
     * @return <code>org.openqa.selenium.WebDriver</code>.
     */
    private static WebDriver createBrowserSpecificDriver() {

        final String browserName = System.getProperty("browser", CHROME);

        WebDriver driver;
        switch (browserName) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case IEXPLORE:
                driver = createInternetExplorerDriver();
                break;
            case SAFARI:
                driver = createSafariDriver();
                break;
            case FIREFOX:
            default:
                driver = createFireFoxDriver();
                break;
        }

        addCommonDriverSettings(driver);

        return driver;
    }

    /**
     * Method to create instance of Chrome web driver.
     *
     * @return <code>org.openqa.selenium.chrome.ChromeDriver</code>.
     */
    private synchronized static WebDriver createChromeDriver() {

        // Selenium driver base path.
        final String driverBasePath = getSeleniumDriverProperty(SELENIUM_DRIVERS_BASE_PATH_KEY);

        // Driver file name.
        String driverFileName = "";

        // For chrome we have to specify the path for external chrome driver.
        switch (Platform.getCurrent()) {
            case WINDOWS:
            case VISTA:
            case XP:
            case WIN8:
            case WIN8_1:
                driverFileName = getSeleniumDriverProperty(CHROME_DRIVER_WINDOWS_FILENAME_KEY);
                break;
            case LINUX:
                driverFileName = getSeleniumDriverProperty(CHROME_DRIVER_LINUX_FILENAME_KEY);
                break;
            case MAC:
                driverFileName = getSeleniumDriverProperty(CHROME_DRIVER_MAC_FILENAME_KEY);
                break;
            default:
                break;
        }

        // Finally set the value for chrome driver system property.
        System.setProperty(getSeleniumDriverProperty(CHROME_DRIVER_SYSTEM_PROPERTY_KEY),
                        driverBasePath + driverFileName);

        // Return driver instance.
        return new ChromeDriver();
    }

    /**
     * Method to create instance of Internet Explorer web driver.
     *
     * @return <code>org.openqa.selenium.ie.InternetExplorerDriver</code>.
     */
    private synchronized static WebDriver createInternetExplorerDriver() {

        // Selenium driver base path.
        final String driverBasePath = getSeleniumDriverProperty(SELENIUM_DRIVERS_BASE_PATH_KEY);

        // Name of the driver file.
        final String driverFileName = getSeleniumDriverProperty(IE_DRIVER_WINDOWS_FILENAME_KEY);

        // For IE we have to specify the path for external IE driver.
        System.setProperty(getSeleniumDriverProperty(IE_DRIVER_SYSTEM_PROPERTY_KEY), driverBasePath + driverFileName);

        // Return the driver instance.
        return new InternetExplorerDriver();
    }

    /**
     * Method to create instance of Safari web driver.
     *
     * @return <code>org.openqa.selenium.safari.SafariDriver</code>.
     */
    private synchronized static WebDriver createSafariDriver() {
        return new SafariDriver();
    }

    /**
     * Method to create FireFoxDriver instance by setting FirefoxProfile and
     * DesiredCapabilities.
     *
     * @return <code>org.openqa.selenium.firefox.FirefoxDriver</code>.
     */
    private synchronized static WebDriver createFireFoxDriver() {

        // Selenium driver base path.
        final String driverBasePath = getSeleniumDriverProperty(SELENIUM_DRIVERS_BASE_PATH_KEY);

        // Driver file name.
        String driverFileName = "";

        // For firefox we have to specify the path for external gecko driver.
        switch (Platform.getCurrent()) {
            case WINDOWS:
            case VISTA:
            case XP:
            case WIN8:
            case WIN8_1:
                switch (System.getProperty("sun.arch.data.model")) {
                    case "64":
                    case "32":
                    default:
                        driverFileName = getSeleniumDriverProperty(GECKO_DRIVER_WINDOWS_FILENAME_KEY);
                        break;
                }
                break;
            case LINUX:
                driverFileName = getSeleniumDriverProperty(GECKO_DRIVER_LINUX_FILENAME_KEY);
                break;
            case MAC:
                driverFileName = getSeleniumDriverProperty(GECKO_DRIVER_MAC_FILENAME_KEY);
                break;
            default:
                break;
        }

        // Finally set the value for Gecko driver system property.
        System.setProperty(getSeleniumDriverProperty(GECKO_DRIVER_SYSTEM_PROPERTY_KEY),
                        driverBasePath + driverFileName);

        final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        return new FirefoxDriver(capabilities);
    }

    /**
     * Method to do add all common settings applicable for browser drivers.
     * 
     * @param driver
     *            <code>org.openqa.selenium.WebDriver</code>.
     */
    private static void addCommonDriverSettings(final WebDriver driver) {

        // Set the timeout for the pages.
        driver.manage().timeouts().implicitlyWait(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);

        // Start position of the browser. 0, 0 will open the browser from top
        // left corner.
        driver.manage().window().setPosition(new Point(0, 0));

        // Browser window will be adjusted based on the current monitor
        // resolution.
        final java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(dim);
    }

    /**
     * Browser clean-up thread class to perform browser closing action.
     *
     * @author a120065
     */
    private static class BrowserCleanup implements Runnable {
        public void run() {
            close();
        }
    }

    /**
     * Method to close the browser.
     */
    public static void close() {
        try {
            if (webDriver != null) {
                LOGGER.info("Closing the browser...");

                webDriver.quit();
                webDriver = null;
            }
        } catch (final UnreachableBrowserException e) {
            LOGGER.error("Cannot close browser: unreachable browser", e);
        }
    }
}
