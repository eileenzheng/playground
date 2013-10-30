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



