package com.cheapflights.abstractpages;

import com.cheapflights.factory.SearchPageFactory;
import com.cheapflights.tools.WebDriverTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractHomePage {
    protected static WebDriver driver;

    public AbstractHomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(name = "origin")
    protected WebElement origin;

    @FindBy(name = "destination")
    protected WebElement destination;

    @FindBy(xpath = "//*[contains(text(), 'Find deals')]")
    protected WebElement submitButton;

    private static By logoXpath = By.xpath("//div[contains(@class, 'logo')]//a[@href='/']");

    public AbstractHomePage chooseOrigin(String from) {
        return this;
    }

    public AbstractHomePage chooseDestination(String to) {
        return this;
    }

    public AbstractHomePage chooseDates(String period, String startDate, String endDate) {
        return this;
    }

    public AbstractHomePage increaseNumberOfAdults(int number) {
        return this;
    }

    public AbstractSearchPage submitForm() {
        submitButton.click();
        String parentWindow = driver.getWindowHandle();
        for (String childWindow : driver.getWindowHandles()) {
            if (childWindow != parentWindow) {
                driver.switchTo().window(childWindow);
            }
        }
        WebDriverTools.waitForVisibilityFluently(driver, driver.findElement(logoXpath), 40, 5);
        return SearchPageFactory.getCorrectPage(driver);
    }


}