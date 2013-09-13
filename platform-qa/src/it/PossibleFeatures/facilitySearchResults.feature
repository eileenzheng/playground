@PUI-206
Feature: Facility Search

  As a member, I want to see results from my search,
  so I can browse facilities.

Scenario: Display facility results from a member's search
  Given I want to view facility search results
  When I execute a search in "10001"
  Then display the results
  And display 10 results as the default
  And display a "See more" Link that reveals 10 more results at a time

