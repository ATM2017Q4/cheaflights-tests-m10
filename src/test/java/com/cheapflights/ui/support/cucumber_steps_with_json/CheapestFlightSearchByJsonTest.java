package com.cheapflights.ui.feature.with_json;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(strict = true, features = "src/test/resources/cucumber-features/find_cheapest_flight_json.feature", glue = {
        "com.cheapflights.ui.feature.with_json" })
public class CheapestFlightSearchByJsonTest extends AbstractTestNGCucumberTests{


}
