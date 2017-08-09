/*
 * Copyright: 2016, Worldline UK&I.
 */
package com.automation.bdd.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to load selenium driver properties from property file.
 * 
 * @author a120065
 */
public class SeleniumDriverProperties implements Serializable {

    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = 5365926099903426875L;

    /**
     * Logger instance for doing logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumDriverProperties.class);

    /**
     * Singleton instance of selenium driver properties.
     */
    private static Properties seleniumDriverProperties;

    /**
     * Constant with selenium driver properties file name.
     */
    public static final String SELENIUM_DRIVER_PROPERTIES_FILENAME = "selenium-driver.properties";

    /**
     * Constant with key to fetch the Chrome driver system property.
     */
    public static final String CHROME_DRIVER_SYSTEM_PROPERTY_KEY = "chrome.driver.system.property";

    /**
     * Constant with key to fetch the Gecko driver system property.
     */
    public static final String GECKO_DRIVER_SYSTEM_PROPERTY_KEY = "gecko.driver.system.property";

    /**
     * Constant with key to fetch the IE driver system property.
     */
    public static final String IE_DRIVER_SYSTEM_PROPERTY_KEY = "ie.driver.system.property";

    /**
     * Constant with key to fetch the selenium driver base path.
     */
    public static final String SELENIUM_DRIVERS_BASE_PATH_KEY = "selenium.drivers.base.path";

    /**
     * Constant with key to fetch the chrome driver file name for Windows OS.
     */
    public static final String CHROME_DRIVER_WINDOWS_FILENAME_KEY = "chrome.driver.windows.filename";

    /**
     * Constant with key to fetch the chrome driver file name for Linux OS.
     */
    public static final String CHROME_DRIVER_LINUX_FILENAME_KEY = "chrome.driver.linux.filename";

    /**
     * Constant with key to fetch the chrome driver file name for MAC OS.
     */
    public static final String CHROME_DRIVER_MAC_FILENAME_KEY = "chrome.driver.mac.filename";

    /**
     * Constant with key to fetch the gecko driver file name for Windows OS.
     */
    public static final String GECKO_DRIVER_WINDOWS_FILENAME_KEY = "gecko.driver.windows.filename";

    /**
     * Constant with key to fetch the gecko driver file name for Linux OS.
     */
    public static final String GECKO_DRIVER_LINUX_FILENAME_KEY = "gecko.driver.linux.filename";

    /**
     * Constant with key to fetch the gecko driver file name for MAC OS.
     */
    public static final String GECKO_DRIVER_MAC_FILENAME_KEY = "gecko.driver.mac.filename";

    /**
     * Constant with key to fetch the IE driver file name for Windows OS.
     */
    public static final String IE_DRIVER_WINDOWS_FILENAME_KEY = "ie.driver.windows.filename";

    /**
     * Utility class should not have default or public constructor.
     */
    private SeleniumDriverProperties() {
        throw new UnsupportedOperationException();
    }

    /**
     * Method to load the selenium driver properties from
     * selenium-driver.properties file from the classpath.
     */
    public static void loadSeleniumDriverProperties() {

        if (seleniumDriverProperties == null) {
            seleniumDriverProperties = new Properties();
            try {
                seleniumDriverProperties.load(SeleniumDriverProperties.class.getClassLoader()
                                .getResourceAsStream(SELENIUM_DRIVER_PROPERTIES_FILENAME));
            } catch (final IOException e) {
                LOGGER.error("Failed to load application properties.", e);
            }
        }
    }

    /**
     * Method to get property value based on the key passed from Selenium Driver
     * properties.
     * 
     * @param key
     *            String of the property.
     * @return value String of the property.
     */
    public static String getSeleniumDriverProperty(final String key) {

        if (seleniumDriverProperties != null && seleniumDriverProperties.containsKey(key)) {
            return seleniumDriverProperties.getProperty(key);
        }

        return "";
    }
}
