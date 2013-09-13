@PUI-51
Feature: Cost Estimator

  As a PPO Member,
  I want to see my estimated responsibility with an individual provider
  so that I can understand how much an individual provider may cost me
  and that the estimate is not an exact cost.

  @wip
  Scenario: Member is able to see the average estimated cost for a procedure
    Given I want to see the average cost of a procedure
    When I search for a provider in "10001" who performs "Knee Surgery"
    Then display a list of results
    And click on a provider
    And view the estimated cost for the procedure

