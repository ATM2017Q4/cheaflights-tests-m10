package com.cheapflights.ui.support.cucumber_steps_without_json;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(strict = true, features = "src/test/resources/cucumber-features/find_cheapest_flight.feature", glue = {
        "com.cheapflights.ui.support.cucumber_steps_without_json" })
public class CheapestFlightSearchTest extends AbstractTestNGCucumberTests{


}
