Feature: Moderation Manager Filters

  Background:
    Given I logged into the moderation manager
    Then I will see the main view

  Scenario:
    When I click 'Select All' under Moderation Status
    And I click the Filter button
    Then I will see reviews in all status

  Scenario:
    When I click 'Escalated' under Moderation Status
    And I click the Filter button
    Then I will see reviews in Escalated status