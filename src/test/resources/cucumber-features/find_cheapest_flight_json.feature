Feature: Find cheapest flight

  Users should be able to get the list of flights between two cities by entering the cities' names,
  departure and return dates and choosing the number of adults.
  Afterwards, user should be able to extract the cheapest and most convenient flight for them by applying filters and sorting by cheapest.

  Background:
    Given I am on the Home Page

  Scenario: Searching for the flights
    Given I have filled in the form on the Home Page with the following values:
      """
    {
    "origin": "Vienna",
    "destination": "London",
    "departureDates": {
    "day": 1,
    "month": "November",
    "year": 2018
    },
    "returnDates": {
    "day": 17,
    "month": "November",
    "year": 2018
    },
    "numberOfAdults": 1
    }
      """

    And submitted the form
    When I chose non-stop flights only
    And set Flight Leg duration to the quarter of max possible
    And sorted the list by cheapest
    Then the cheapest flight costing less than 200 is shown first in the list

