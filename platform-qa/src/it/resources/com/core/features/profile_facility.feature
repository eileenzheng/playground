@facility
Feature: Facility Profile

  In order to demonstrate excellence in general and in specific arenas
  As a member I want to see awards for a facility
  So that I can feel confident that a facility is good and understand specific areas where they excel

  Background:

    Given I am viewing a facility with awards
    Then I will see the awards module

  @PUI-260 @awards
  Scenario: Facility Awards More Than Three Awards

    When up to 3 awards will display on the module
    And I will see a link that reads "View X More Awards"

  @PUI-260 @awards
  Scenario: Facility Awards Visible Information

    Given the header displays as "Awards"
    Then I will see the award name
    And I will see the award logo

  @PUI-260 @awards
  Scenario: Facility Awards associated urls with logo

    When a url has an associated link
    Then the link will be with the logo

  @PUI-260 @awards
  Scenario: Facility Awards associated urls with no logo

    When a url has an associated link
    Then the link will be with the award name

  @PUI-260 @PUI-262 @awards
  Scenario: Facility Awards grouping

    Given I am viewing a facility with awards
    Then I will see the awards module
    And the awards are grouped by category

  @PUI-303 @specialties
  Scenario: Facility Specialties

    In order to demonstrate to members what a facility specializes in
    As a member
    I want to see specialties for a facility
    So that I can decide if a facility has expertise in an area of care that is important to me

    Given I am viewing a facility with at least 1 facility
    Then I will see the facility specialties module
    And the module header displays as "Specialties & Expertise"
    And specialties will be listed in alphabetical order

  @PUI-316 @HCSC @identifiers
  Scenario: Facility Identifiers
    In order to allow members to identify facilities to a customer service representative
    As a member
    I want to see identifiers for a facility
    So that I can uniquely identify the facility to an HCSC customer service agent.

    Given I am viewing a facility with identifiers
    Then I will see up to 3 identifiers
    And it will be displayed as [identifier label][colon][identifier]
    #And it will display if specific to that requested network

  @PUI-235 @card
  Scenario: Facility Card Visible Elements
    In order to allow members to see top level information about a provider
    As a member
    I want to summary information on a facility and its location
    so that i can easily decide if i am interested in the facility.

    Given I am viewing a facility
    Then I will see the facility name
    And I will see the facility address
    And I will see a get directions link
    And I will see a facility phone number
    And I will see the tier network name
    And I will see a photo
    And I will see an identifier label and number
    And I will see a facility fax number



