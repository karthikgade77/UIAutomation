#Author: Karthik Reddy
#Date: 02-Sept-2018
Feature: Flight booking

  @Booking
  Scenario Outline: Bill wants to travel from DXB to LHR
    Given that Bill has decided to check available flights
    When he looks at a return trip from <depart> to <arrival> leaving one week from now
    Then he should be shown the cheapest return ticket from <depart> to <arrival>

    Examples: 
      | depart | arrival |
      | DXB    | LHR     |
