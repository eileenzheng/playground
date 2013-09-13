@PUI-133
Feature: Facility Search

  In order to decide what facility I am interested in viewing,
  as a Member,
  I want to see basic location information for each facility in the results set.

Scenario: Display basic location information for each facility
  Given I am on the home page
  When I search for a provider in "10001"
  Then display a list of results
  And confirm facility name, location name, location specialties, location address, and location phone number are visible
