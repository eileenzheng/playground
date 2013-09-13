@PUI-30
Feature: Cost Estimator

  So that I can quickly see what portion I will owe is verses the total cost of the procedure,
  as a PPO Member,
  I want to see an overview of how much the procedure will cost
  and what my responsibility is.

  Scenario: Member wants to see how much they will owe vs the cost of the procedure
    Given the user searched for a provider in "10001" who performs "Knee Surgery"
    When I click on a provider
    Then display overall cost as a donut
    And see that the costs for member and payer are separate
    And that the text to the right of the donut explains the responsibility and total cost

  Scenario: Member wants to see how much they will owe when they are responsible for full costs
    Given the user searched for a provider in "10001" who performs "Knee Surgery"
    When I click on a provider
    Then display overall cost as a donut
    And see that the non-paying party is not visualized at all
