@provider
Feature: Provider Profile

  In order to demonstrate excellence in general and in specific arenas
  As a member I want to see awards for a Provider
  So that I can feel confident that a Provider is good and understand specific areas where they excel

  Background:
    Given I am viewing a Provider with awards
    Then I will see the awards module
    And the header displays as "Awards"
    And I will see the award name
#    And I will see the award logo

  @PUI-355 @awards
  Scenario: Provider Awards More Than Three Awards

    When a provider has more than 1 awards
    And up to 3 awards will display on the module
    And I will see a link that reads "View X More Awards"

  @PUI-355 @awards
  Scenario: Provider Awards associated urls with logo

    When an award has an associated link
    Then the link will be with the logo

  @PUI-355 @awards
  Scenario: Provider Awards associated urls with no logo

    When an award has an associated link
    Then the link will be with the award name

  @PUI-355 @awards
  Scenario: Provider Awards grouping

    And the awards are grouped by category

  @PUI-356 @awards
  Scenario: Provider Awards Expanded grouping

    When I click See Awards and Recognitions
    Then I will see the awards are grouped by category

  @ignore @PUI-303 @specialties
  Scenario: Provider Specialties

    In order to demonstrate to members what a Provider specializes in
    As a member
    I want to see specialties for a Provider
    So that I can decide if a Provider has expertise in an area of care that is important to me

    Given I am viewing a Provider with at least 1 Provider
    Then I will see the Provider specialties module
    And the module header displays as "Specialties & Expertise"
    And specialties will be listed in alphabetical order





