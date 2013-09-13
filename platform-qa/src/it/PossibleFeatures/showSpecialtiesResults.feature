@PUI-133
Feature: Facility Search

  In order to decide what facility I am interested in viewing
  As a Member
  I want to see all the specialties a facility provides in my network.

Scenario: Display specialty information for each facility
  Given I want to see facility's specialty information
  When I search for a provider in "10001"
  Then display a list of results
  And all in scope and out of scope specialties a facility is contracted for in the searched network are displayed on the results record.
  And the listed specialties are in order of search sort order (in scope data comes before out of scope data)
  And each specialty is displayed only once in the list
