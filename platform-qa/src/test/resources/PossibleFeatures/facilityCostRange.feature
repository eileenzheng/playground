@PUI-49
Feature: Cost Estimator

  As a PPO Member,
  I want to see a range of costs for all providers in my results set,
  so I can understand the range of costs I might be responsible for a procedure in my searched area.

  Scenario: Members are able to see a range representing the lowest average calculated member cost and the highest average calculated member cost in the results set.

  Scenario: Members are also able to see a the lowest average calculated payer contribution and the highest average calculated payer cost (i.e. the average total cost - the average calculated member cost - HSA contribution)

  Scenario: If accumulators are not available, the member and employer calculations are based on an assumed $0 accumulation (i.e. the amount the member has spent to date is not counted in the calculation).

  Scenario: If member benefit limits (e.g. deductible, out of pocket max) are not available, the estimates are not available.

  Scenario: If all providers in the results set have the same average calculated member cost or payer contribution, cost is displayed as a single dollar value and not a range.

  Scenario: price range always reads low to high
