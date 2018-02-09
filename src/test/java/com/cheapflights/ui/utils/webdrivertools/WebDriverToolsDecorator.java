package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverToolsDecorator implements IWebDriverTools {
    private WebDriverTools webDriverTools;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public WebDriverToolsDecorator(WebDriverTools webDriverTools) {
        this.webDriverTools = webDriverTools;
    }

    public void waitForVisibilityFluently(WebDriver driver, WebElement element, int timeout, int poll) {
        try {
            webDriverTools.waitForVisibilityFluently(driver, element, timeout, poll);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.log(Level.SEVERE, "WebDriver failed to find the element within the specified time frame");
        } catch (org.openqa.selenium.NoSuchElementException e){
            logger.log(Level.SEVERE, "WebDriver was unable to locate the element");
        }
    }

    public void waitForInvisibilityExplicitly(WebDriver driver, WebElement element, int timeout) {
        try {
            webDriverTools.waitForInvisibilityExplicitly(driver, element, timeout);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.log(Level.SEVERE, "WebDriver failed to find the element within the specified time frame");
        } catch (org.openqa.selenium.NoSuchElementException e){
            logger.log(Level.SEVERE, "The driver was unable to locate the element");
        }
    }

    public void waitForAttributeToBe(WebDriver driver, By by, String attribute, String value, int timeout) {
        try {
            webDriverTools.waitForAttributeToBe(driver, by, attribute, value, timeout);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.log(Level.SEVERE, "WebDriver failed to find the element within the specified time frame");
        } catch (org.openqa.selenium.NoSuchElementException e){
            logger.log(Level.SEVERE, "The driver was unable to locate the element");
        }
    }

    public boolean waitForJSandJQueryToLoad(WebDriver driver) {
        try {
            webDriverTools.waitForJSandJQueryToLoad(driver);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.log(Level.SEVERE, "The page failed failed to properly load within the specified time frame");
            return false;
        }
        return true;
    }
}
