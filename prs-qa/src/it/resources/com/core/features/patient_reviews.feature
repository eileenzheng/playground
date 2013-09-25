Feature: Patient Reviews

  Background:
    Given I have navigated to a provider review page
    Then I will see patient reviews

  Scenario: Page navigation forward
    Given more than one page of reviews
    And the page display says "1" of X
    When I click ">>"
    Then the page display says "2" of X

  Scenario: Page navigation back
    Given more than one page of reviews
    And I click ">>"
    And the page display says "2" of X
    When I click "<<"
    Then the page display says "1" of X

  Scenario: Navigate to review this doctor
    When I click review this doctor
    Then I will be brought to the Terms of Use page

  Scenario Outline: Change the sorting
    When I select sort option <sort>
    Then I will see the sort order change

  Examples:
    | sort |
    | Date: Newest to Oldest |
    | Date: Oldest to Newest |
    | Most Helpful |
    | Least Helpful |
    | Experience: Lowest to Highest |
    | Experience: Highest to Lowest |


