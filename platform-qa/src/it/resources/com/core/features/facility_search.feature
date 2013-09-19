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
    And I will see the location specialties if they exist
    And I will see a location address if it exists
    And I will see a location phone number if it exists
    And I will see the location name if different from the facility name

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

  @PUI-135
  Scenario: Show Specialties on a Results Record

    In order to decide what facility I am interested in viewing
    As a Member
    I want to see all the specialties a facility provides in my network.

    When a search for a facility is completed
    And I view a facility from the search results
    Then I will see the location specialties if they exist
    And the specialties will only be displayed once
    And the specialties will be displayed in order of in scope data to out of scope data

  @PUI-206
  Scenario: Facility Search Results

    As a member
    I want to see results from my search
    so I can browse facilities

    When there are more than 10 records
    Then up to 10 results are displayed by default

  @PUI_51
  Scenario: Facility Search Result Cost Display

    In order to demonstrate cost differentials between providers
    As a PPO Member,
    I want to see my estimated responsibility with an individual provider
    so that I can understand how much a procedure with individual provider may cost me

    When a search for a facility is completed
    And I view a facility from the search results
    Then I will see an average cost for the provider

  @ignore
  Scenario: Facility Search Result has not Cost Display
    When a search for a facility is completed
    And I view a facility from the search results
    And the cost is not available for the facility
    Then I will see "Cost N/A" displayed for that provider

  @ignore
  Scenario: Facility Search Result has more than one location
    When a search for a facility is completed
    And I view a facility from the search results
    And it has more than one location
    Then I will see a link

  @PUI-49
  Scenario: Display range of facility costs

    In order to demonstrate price differentials between providers
    As a PPO Member,
    I want to see a range of costs for all providers in my results set,
    so I can understand the range of costs I might be responsible for a procedure in my searched area.

    When a search for a facility is completed
    Then I will see a range of costs for all providers
    And the range will display for "Shoulder Arthroscopy"
    And the cost will be "$650-1,125"
    And the plan will contribute "$4,849-11,877"

  @ignore
  Scenario: Display single dollar value when all providers have the same average value
    When a search for a facility is completed
    And all the providers have the same average cost
    Then I will see a single dollar value
    And the cost will be ""
