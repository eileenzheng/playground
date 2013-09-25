Feature: Review a Provider Error Scenarios

  Scenario Outline: Rate doctors without all required fields
    Given I am rating doctor with id "<doctor>"
    And I have accepted the terms of use
    And I have checked the verify I have received services checkbox
    Then I will rate my experience as "<experience>"
    And I will make my recommendation as "<recommendation>"
    And I click submit
    Then I will see the following error "<error>"

  Examples: Invalid scenarios
    | doctor | experience | recommendation | error |
    | 1      | 5          |                | Recommend |
    | 1      |            | Y              | Experience |
