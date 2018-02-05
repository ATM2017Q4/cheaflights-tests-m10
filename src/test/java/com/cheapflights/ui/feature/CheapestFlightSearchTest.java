package com.cheapflights.ui.feature;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(strict = true, features = "src/test/resources/cucumber-features/find_cheapest_flight.feature", glue = {
        "com.cheapflights.ui.feature" })
public class CheapestFlightSearchTest extends AbstractTestNGCucumberTests{


}
