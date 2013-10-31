@provider @awards @PUI-355 @PUI-356
Feature: Professional Profile Awards

  In order to demonstrate excellence in general and in specific arenas
  As a member I want to see awards for a Professional
  So that I can feel confident that a Professional is good and understand specific areas where they excel

  Background:

    Given I am viewing a professional with id 1000000000
    Then I will see the awards module
    And the header displays as "Awards & Recognitions"
    And I will see the award name
    And the professional has at least 1 award
#    And I will see the award logo

  #PUI-355
  Scenario: Professional With More Than Three Awards

    Given a professional has more than 3 awards
    Then up to 3 awards will display on the module
    And I will see a link that reads "See more Awards & Recognitions..."

  #PUI-355
  Scenario: Professional Awards associated urls with logo

    Given an award has an associated link
    And I see an award logo
    Then the link will be with the logo

  #PUI-355
  Scenario: Professional Awards associated urls with no logo

    When an award has an associated link
    And I don't see an award logo
    Then the link will be with the award name

  #PUI-356
  Scenario: Professional Awards Expanded grouping

    When I click See Awards and Recognitions
    Then I will see the awards are grouped by category