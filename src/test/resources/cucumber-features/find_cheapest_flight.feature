Feature: Find cheapest flight

  Users should be able to get the list of flights between two cities by entering the cities' names,
  departure and return dates and choosing the number of adults.
  Afterwards, user should be able to extract the cheapest and most convenient flight for them by applying filters and sorting by cheapest.

  Background:
    Given I want to use Firefox browser
    And I am on the Home Page

  Scenario Outline: Searching for the flights
    Given I have searched for all airports in <origin>
    And searched for all airports in <destination>
    And searched for <departureMonth>, <departureDay>, <returnDay> in the date picker
    And set number of adults to <numberOfAdults>
    And submitted the form
    When I choose non-stop flights only
    And set duration to the quarter of max possible
    And sort the list by cheapest
    Then the cheapest flight costing less than <acceptablePrice> is shown first in the list

    Examples:
      | origin | destination | departureDay | departureMonth | returnDay | numberOfAdults | acceptablePrice |
      | Moscow | Tokyo       | 7            | October        | 20        | 2              | 600             |
      | Vienna | London      | 1            | March          | 17        | 1              | 200             |

