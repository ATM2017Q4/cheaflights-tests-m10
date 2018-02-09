package com.cheapflights.ui.support.cucumber_steps_with_json;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(strict = true, features = "src/test/resources/cucumber-features/find_cheapest_flight_json.support", glue = {
        "com.cheapflights.ui.support.cucumber_steps_with_json" })
public class CheapestFlightSearchByJsonTest extends AbstractTestNGCucumberTests{


}
