@Professional
Feature: Professional Profile

  Background:
    Given I am viewing a professional with id 1000000000

  #PUI-303
  @ignore @specialties
  Scenario: Professional Specialties

    In order to demonstrate to members what a Professional specializes in
    As a member
    I want to see specialties for a Professional
    So that I can decide if a Professional has expertise in an area of care that is important to me

    Given I am viewing a Professional with at least 1 Specialty
    Then I will see the Professional specialties module
    And the module header displays as "Specialties & Expertise"
    And specialties will be listed in alphabetical order

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




