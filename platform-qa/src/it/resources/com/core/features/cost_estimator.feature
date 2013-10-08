@cost_estimator @HCSC
Feature: Cost Estimator

  @PUI-30
  Scenario: Facility Cost Overview
  In order to express the value of a member's benefits as a PPO Member,
  I want to see an overview of how much the procedure will cost and what my responsibility is in a facility profile box
  So that I can quickly see what portion I will owe is verses the total cost of the procedure

    Given I have searched doctors that perform "Shoulder Arthroscopy"
    When I select doctor "John A Damergis" from the results
    Then I will see a cost estimate box for "Shoulder Arthroscopy"
    And I will see a break down of what I owe vs the total cost


