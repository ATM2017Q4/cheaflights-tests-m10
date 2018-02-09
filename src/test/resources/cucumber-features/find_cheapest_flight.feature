Feature: Find cheapest flight

  User should be able to get the list of flights between two cities by entering the cities' names,
  departure and return dates and choosing the number of adults.
  As a result, user should be able to filter the cheapest and most convenient flight for them
  by applying filters and sorting by cheapest.

  Background:
    Given I am on the Home Page

  Scenario Outline: Searching for the cheapest flight
    Given I want to travel from <origin> to <destination>
    And chose it to last from <departureMonth> <departureDay> till <returnDay> of the same month
    And want to find the ticket for <numberOfAdults> adults
    And submitted the form
    When I chose non-stop flights only
    And set Flight Leg duration to the quarter of max possible
    And sorted the list by cheapest
    Then the cheapest flight costing less than <acceptablePrice> is shown first in the list

    Examples:
      | origin | destination | departureDay | departureMonth | returnDay | numberOfAdults | acceptablePrice |
      | Moscow | Tokyo       | 7            | October        | 20        | 2              | 600             |
      | Vienna | London      | 1            | March          | 17        | 1              | 200             |

