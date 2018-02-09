package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IWebDriverTools {
    void waitForVisibilityFluently(WebDriver driver, WebElement element, int timeout, int poll);

    void waitForInvisibilityExplicitly(WebDriver driver, WebElement element, int timeout);

    void waitForAttributeToBe(WebDriver driver, By by, String attribute, String value, int timeout);

    boolean waitForJSandJQueryToLoad(WebDriver driver);

}
