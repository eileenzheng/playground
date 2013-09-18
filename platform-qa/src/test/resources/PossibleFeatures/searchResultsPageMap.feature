@PUI-207
Feature: Facility Search

  As a member I want to see my facility results plotted on a map
  so I can get a visual understanding of where facilities are located.

  Scenario: Show map in small view by default
    Given I want to see facilities plotted on a map
    When I execute a search for facilities in "10001"
    Then display a map with a pin for each facility location
    And it will include the initial search results
    And hovering over a pin will highlight its search result record
    And hovering over a search result record will highlight a pin

  Scenario: Show more pins on the map if there are more records
    Given I want to view more locations on the map
    When I scroll down to display more records
    Then the map displays more pins to represent the new records

  Scenario: Display "Show more locations" link if a facility has 2+ locations
    Given a facility that has 2 or more locations
    When I view it in search results
    Then display a "Show more locations" link
    And display a double pin icon

  Scenario: Display information for each map pin if it is clicked
    Given an expanded search results map that has pins
    When I click on a pin
    Then display an info bubble with the facility address