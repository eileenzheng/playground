@facility
Feature: Facility Profile Awards

  In order to demonstrate excellence in general and in specific arenas
  As a member I want to see awards for a facility
  So that I can feel confident that a facility is good and understand specific areas where they excel

  Background:

    Given I am viewing a facility with awards
    Then I will see the awards module

  @PUI-260
  Scenario: Facility Awards More Than Three Awards

    When up to 3 awards will display on the module
    And I will see a link that reads "View X More Awards"

  @PUI-260
  Scenario: Facility Awards Visible Information

    Given the header displays as "Awards"
    Then I will see the award name
    And I will see the award logo

  @PUI-260
  Scenario: Facility Awards associated urls with logo

    When a url has an associated link
    Then the link will be with the logo

  @PUI-260
  Scenario: Facility Awards associated urls with no logo

    When a url has an associated link
    Then the link will be with the award name

  @PUI-260 @PUI-262
  Scenario: Facility Awards grouping

    Given I am viewing a facility with awards
    Then I will see the awards module
    And the awards are grouped by category

  @PUI-303
  Scenario: Facility Specialties

    In order to demonstrate to members what a facility specializes in
    As a member
    I want to see specialties for a facility
    So that I can decide if a facility has expertise in an area of care that is important to me

    Given I am viewing a facility with at least 1 facility
    Then I will see the facility specialties module
    And the module header displays as "Specialties & Expertise"
    And specialties will be listed in alphabetical order





