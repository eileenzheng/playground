@Professional
Feature: Professional Profile

  Background:
    Given I am viewing a professional with id 1000000000

  @ignore @PUI-303 @specialties
  Scenario: Professional Specialties

    In order to demonstrate to members what a Professional specializes in
    As a member
    I want to see specialties for a Professional
    So that I can decide if a Professional has expertise in an area of care that is important to me

    Given I am viewing a Professional with at least 1 Professional
    Then I will see the Professional specialties module
    And the module header displays as "Specialties & Expertise"
    And specialties will be listed in alphabetical order





