@search
Feature: Facility Search

  Background:
    Given I have done a zip code search for a facility
    And I have submitted the search results

  @PUI-133
  Scenario: Basic Facility Data Results

    In order to decide what facility I am interested in viewing
    as a Member
    I want to see basic location information for each facility in the results set.

    When a search for a facility is completed
    Then I will see records for facilities that contain a name
    And I will see the location name if different from the facility name
    And I will see the location specialties if they exist
    And I will see a location address if it exists
    And I will see a location phone number if it exists

  @PUI-207
  Scenario: Search Results Map

    As a member
    I want to see my facility results plotted on a map
    so I can get a visual understanding of where facilities are located

    When a search for a facility is completed
    Then I will see a small map displaying the facilities
#    And there is a bigger map button to expand the map
#    And there is a smaller map button to reduce the map
#    And display a double pin icon if a facility has 2+ locations
#    And hovering over a pin highlights the result record
#    And hovering over a result record highlights the pin
#    And clicking on each pin display an info bubble with the facility address
#    And display more pins if infinite scroll adds more results

#  @PUI-135
#  Scenario: Show Specialties on a Results Record
#
#    In order to decide what facility I am interested in viewing
#    As a Member
#    I want to see all the specialties a facility provides in my network.
#
#    When a search for a facility is completed
#    And I select a facility from the search results
#    Then I will see all the specialties this facility supports
#    And the specialties will be displayed in order of in scope data to out of scope data
#    And the specialties will only be displayed once

#  @PUI-206
#  Scenario: Facility Search Results
#
#    As a member
#    I want to see results from my search
#    so I can browse facilities
#
#    When there are more than 10 records
#    Then up to 10 results are displayed by default
#    And a See More link is displayed
