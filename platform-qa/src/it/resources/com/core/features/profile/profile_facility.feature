@facility
Feature: Facility Profile

  Background:

    Given I am viewing a facility with id 1000012717

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

    Then I will see the facility name
    And I will see the facility address
    And I will see a get directions link
    And I will see a facility phone number
    And I will see the tier network name
    And I will see a photo
    And I will see an identifier label and number
    And I will see a facility fax number

  #PUI-301
  @location_amenities
  Scenario: Location Amenities Visible Elements
    In order to make members aware of important amenities offered at a location
    As a member
    I want to see location amenities
    So I can consider valuable amenities when deciding which location to see a facility at

    Given the amenity module is visible
    Then I will see the module title is "Services at this Location"
    And I will see "Handicap Accessible"
#    And I will see "Electronic Prescriptions"
#    And I will see "Electronic Health Record"
    And I will see an amenity icon to the left of the name



