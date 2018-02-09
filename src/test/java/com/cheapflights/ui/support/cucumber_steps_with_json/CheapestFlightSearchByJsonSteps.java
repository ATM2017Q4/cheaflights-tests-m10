package com.cheapflights.ui.support.cucumber_steps_with_json;

import com.cheapflights.common.driver.AbstractWebDriver;
import com.cheapflights.common.driver.DriverFactory;
import com.cheapflights.ui.entities.TravelInfo;
import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;
import com.cheapflights.ui.page.factory.HomePageFactory;
import com.cheapflights.ui.page.factory.SearchPageFactory;
import com.google.gson.Gson;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class CheapestFlightSearchByJsonSteps {


    private final String url = "https://cheapflights.com/";
    private WebDriver driver;
    private AbstractHomePage homePage;
    private AbstractSearchPage searchPage;
    private HomePageFactory pageFactory;


    @Before
    public void launchBrowser() {
        AbstractWebDriver instance = DriverFactory.getDriverFromFactory("firefox");
        driver = instance.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Given("^I am on the Home Page$")
    public void openUrl() {
        driver.get(url);
        pageFactory = new HomePageFactory(driver);
        homePage = pageFactory.getCorrectPage(driver);

    }

    @Given("^I have filled in the form on the Home Page with the following values:$")
    public void chooseOrigin(String json) {
        TravelInfo travelInfo = new Gson().fromJson(json, TravelInfo.class);
        homePage.chooseOrigin(travelInfo.getOrigin())
                .chooseDestination(travelInfo.getDestination())
                .chooseDates(travelInfo.getDepartureDates().getMonth(), Integer.toString(travelInfo.getDepartureDates().getDay()), Integer.toString(travelInfo.getReturnDates().getDay()))
                .increaseNumberOfAdults(travelInfo.getNumberOfAdults());
    }

    @And("^submitted the form$")
    public void submitForm() {
        searchPage = homePage.submitForm();
    }

    @When("^I chose non-stop flights only$")
    public void chooseNonStopFlights() {
        searchPage.chooseNonStopFlights();
    }

    @And("^set Flight Leg duration to the quarter of max possible$")
    public void modifyDuration() {
        searchPage.modifyDuration(4, 3);
    }

    @And("^sorted the list by cheapest$")
    public void sortByCheapest() {
        searchPage.sortByCheapest();
    }

    @Then("^the cheapest flight costing less than (\\d+) is shown first in the list$")
    public void getCheapest(int acceptablePrice) {
        Assert.assertTrue(SearchPageFactory.getCorrectPage(driver).getCheapestFlight() <= acceptablePrice);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
