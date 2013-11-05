@Professional
Feature: Professional Profile

  Background:
    Given I am viewing a professional with id 1000000000

  #PUI-335
  @specialties
  Scenario: Professional Specialties

    In order to demonstrate to members what a Professional specializes in
    As a member
    I want to see specialties for a Professional
    So that I can decide if a Professional has expertise in an area of care that is important to me

    Given the specialties module is visible
    Then I will see the module title is "Specialties & Expertise"

  #PUI-335
  @ignore
  Scenario: Professional Specialties No Specialties

  In order to demonstrate to members what a Professional specializes in
  As a member
  I want to see specialties for a Professional
  So that I can decide if a Professional has expertise in an area of care that is important to me

    Given the specialties module is visible
    Then I will see the module title is "Specialties & Expertise"
    And the module displays "Unknown"

  #PUI-350
  @quality-module
  Scenario: Quality Module Visible Elements

    In order to influence a member's decision with meaningful clinical quality metrics
    As a member
    I want to see important quality metrics
    So that I can understand if a professional is any good

    Given the quality module is visible
    Then I will see the module title is "Quality"
    And up to 3 measures are displayed
    And I will see the measure name
    And I will a measure percentage
    And I will see the text "above average"
    And I will see a link that reads "See more details"
    #If there is no graphic, display numeric measure
    #If there is a numerical comparison figure display percentage above local comparison
    #if there is a relative or numerical comparison, visual language for percent higher than local average is consistent with visual language for % higher/lower rated/priced in similar providers
    #If there is a comparison metric, no measure are displayed that are below the measure's comparison metric

  #PUI-350
  @quality-module
  Scenario Outline: Quality Module Example

    Given the quality module is visible
    Then I will see the metric of <Metric>
    And the measure percentage is <Percent>
    And the comparison will be <Comparison>

    Examples: Quality Metrics
    | Metric                      | Percent | Comparison    |
    | Breast Cancer Screening     | 21%     | Above Average |
    | Cervical Cancer Screening   | 13%     | Above Average |
    | Colorectal Cancer Screening | 4%      | Above Average |

  #PUI-272
  @multiple-locations
  Scenario: Professional with multiple locations
    In order to demonstrate that a professional practices at multiple locations
    As a member
    I want to see all locations that I professional practices at
    so that I can find the most convenient location to see the professional.

    Given the professional has multiple locations
    Then I will see a link that reads "X more locations"

  @multiple-locations
  Scenario: View multiple locations list
    Given the professional has multiple locations
    When I click "2 more locations"
    Then I will see a list of locations
    And I will see a link that reads "View profile at this location"

  @multiple-locations
  Scenario: Visible fields for each location
    Given the professional has multiple locations
    When I click "2 more locations"
    Then I will see a list of locations
    And I will see a location name
    And I will see the location address
    And I will see a numbered map pin

  @multiple-locations
  Scenario Outline: Multiple location data check
    Given the professional has multiple locations
    When I click "2 more locations"
    Then I will see a list of locations
    And the name will be <Name>
    And the address will be <Address>

    Examples: Extra locations

    | Name                              | Address                                               |
    | Doctors Memorial Hospital - Perry | 333 N. Byron Butler Pkwy, Perry, FL 32347-2300        |
    | Capital Regional Medical Center   | 2626 Capital Medical Blvd, Tallahassee, FL 32308-4402 |


  #PUI-358
  @staff-languages
  Scenario: Visible fields for languages
    Given the languages module is visible
    Then I will see the module title is "Foreign Languages Spoken"
    And I will see a Languages Spoken by Staff label
    And I will see up to 5 languages
    #And I will see "Spanish,Sundanese" as the languages


  @ignore @staff-languages
  Scenario Outline: Language examples
    Given I am viewing a professional with id <number>
    Then the languages module is visible
    And the languages are <language>

    Examples: Visible Languages
    | number     | language           |
    | 1000000000 | Spanish,Sundanese  |
    | 1000000017 | Spanish            |


  #PUI-485
  @location_amenities
  Scenario: Location Amenities Visible Elements
  In order to make members aware of important amenities offered at a location
  As a member
  I want to see location amenities
  So I can consider valuable amenities when deciding which location to see a facility at

    Given the amenity module is visible
    Then I will see the module title is "Location Amenities"
    And I will see "Handicap Accessible"
  #    And I will see "Electronic Prescriptions"
  #    And I will see "Electronic Health Record"
    And I will see an amenity icon to the left of the name

  @ignore
  Scenario Outline:
    Given I am viewing a professional with id <number>
    Then I will see the following amenity <amenity>
    | number | amenity                   |
    | 123123 | Handicap Accessible       |
    | 374734 | Electronic Prescriptions  |
    | 872348 | Electronic Health Records |
    | 478293 | Public Transportation     |

  #PUI-339 PUI-642 PUI-644
  @affiliations
  Scenario: Affiliations Module is visible
  In order to help members find a provider in their preferred medical group or hospital
  As a member
  I want to see medical group and hospital affiliations
  So that I can find a professional that practices at my preferred medical group or hospital

    Given the affiliations module is visible
    Then I will see the module title is "Affiliations"
    And I will see a subheading called "Hospital Affiliations"
    And I will see a subheading called "Medical Groups"
    And I will see a link that reads "See more affiliations..."

  #PUI-339 PUI-642 PUI-644
  @ignore @affiliations
  Scenario Outline:
    Given I am viewing a professional with id <number>
    Then I will see the following hospital affiliation <hos_affiliations>
    And the "hospital" will be located in <city>

    Examples: Hospital Affiliations
      | number | hos_affiliations                   | city         |
      | 123123 | The University Of Il Medical Ctr   | Chicago, IL  |
      | 123123 | Elmhurst Mem Hospital Main Campus  | Elmhurst, IL |

    Examples: No Hospital Affiliations
      | number | hos_affiliations                   | city |
      | 999999 | None                               |      |
      | 888888 | None                               |      |

  #PUI-339 PUI-642 PUI-644
  @ignore @affiliations
  Scenario Outline:
    Given I am viewing a professional with id <number>
    Then I will see the following group affiliation <group_affiliations>
    And the "group" will be located in <city>

    Examples: Group Affiliations
      | number | group_affiliations                   | city         |
      | 123123 | The University Of Il Medical Ctr     | Chicago, IL  |
      | 123123 | Elmhurst Mem Hospital Main Campus    | Elmhurst, IL |

    Examples: No Group Affiliations
      | number | group_affiliations                   | city |
      | 999999 | None                                 |      |
      | 888888 | None                                 |      |