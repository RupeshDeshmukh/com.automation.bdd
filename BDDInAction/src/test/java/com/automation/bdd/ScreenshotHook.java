/*
 * Copyright: 2013, Atos HTTS.
 */
package com.automation.bdd;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;

/**
 * Hook for creating screen shots after a step is failed.
 * 
 * @author a120065
 */
public class ScreenshotHook {

    /**
     * Take a screenshot after a step failed.
     * 
     * @param result
     *            <code>cucumber.api.Scenario</code>.
     * @throws IOException
     */
    @After
    public void takeScreenshot(final Scenario result) throws IOException {
        if (result.isFailed()) {
            result.embed(createScreenshot(), "image/png");
        }
    }

    /**
     * Create a screenshot and return screen shot data in form of bytes.
     * 
     * @return byte[]
     */
    public byte[] createScreenshot() {

        final WebDriver webDriver = WebDriverFactory.getWebDriverInstance();

        if (webDriver instanceof TakesScreenshot) {
            return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        }

        return new byte[] {};
    }
}
