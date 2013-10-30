@facility @awards @PUI-260 @PUI-262
Feature: Facility Profile Awards

  In order to demonstrate excellence in general and in specific arenas
  As a member I want to see awards for a facility
  So that I can feel confident that a facility is good and understand specific areas where they excel

  Background:

    Given I am viewing a facility with id 1000012717
    Then I will see the awards module
    And the header displays as "Awards"
    And I will see the award name
    And the facility has at least 1 award
#    And I will see the award logo

#PUI-260
  Scenario: Facility Awards More Than Three Awards

    Given a facility has less than 3 awards
    Then I will see a link that reads "View Awards & Recognitions details..."

  #PUI-260
  Scenario: Facility Awards More Than Three Awards

    Given a facility has more than 3 awards
    Then up to 3 awards will display on the module
    And I will see a link that reads "View Awards & Recognitions details..."

  #PUI-260
  Scenario: Facility Awards associated urls with logo

    Given an award has an associated link
    And I see an award logo
    Then the link will be with the logo

  #PUI-260
  Scenario: Facility Awards associated urls with no logo

    When an award has an associated link
    And I don't see an award logo
    Then the link will be with the award name

  #PUI-260 PUI-262
  Scenario: Facility Awards grouping

    When I click See Awards and Recognitions
    And the awards are grouped by category