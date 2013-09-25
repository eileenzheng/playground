Feature: Review a Provider

  Scenario Outline: Rate doctors
    Given I am rating doctor with id "<doctor>"
    And I have accepted the terms of use
    And I have checked the verify I have received services checkbox
    Then I will rate my experience as "<experience>"
    And I will make my recommendation as "<recommendation>"
    And I will rate their communication as "<communication>"
    And I will rate their availability as "<availability>"
    And I will rate their environment as "<environment>"
    And I will provide comments "<comments>"
    And I click submit
    Then I will be brought back to the view reviews page
#    And see my review at the top

  Examples: Valid scenarios
    | doctor | experience | recommendation | communication | availability | environment | comments |
    | 1      | 5          | Y              |               |              |             |          |
    | 2      | 3          | Y              | 5             | 2            | 4           |          |
    | 3      | 1          | N              |               |              |             | Lorem ipsum not good |
    | 4      | 4          | Y              |               | 5            | 2           | Fantastic. Office could be nicer |