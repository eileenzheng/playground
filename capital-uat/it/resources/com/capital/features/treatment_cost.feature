Feature: Search for Treatment

  Given I have logged into SAML
  And I am on the search landing page

  Scenario: Search for treatment cost screen

    When I click "Search for Treatment Cost"
    Then I will see the Search for Treatment Cost screen

  Scenario: Search for treatment cost appearance

    Given I see the Search for Treatment Cost screen
    Then I will see:
    | Keyword Search Window |
    | Address               |
    | Change zip option     |
    | Search button         |

  Scenario: Change address

    Given I see the Search for Treatment Cost screen
    When I click "Change Address"
    Then I will see the same address as before
    And the zip code is populated
    And the zip change field will be collapsed

  Scenario: Change address with invalid zip code

    Given I see the Search for Treatment Cost screen
    When I click "Change Address"
    Then I will see the same address as before
    When I input an invalid zip code
    Then I will see an error message

  Scenario: Change address with a blank zip code

    Given I see the Search for Treatment Cost screen
    When I click "Change Address"
    Then I will see the same address as before
    When I use a blank zip code
    Then I will see an error message

  Scenario: Autosuggest appearance

    Given I see the Search for Treatment Cost screen
    When I type at least 1 character
    Then the autosuggestion dropdown will appear

  Scenario: Autosuggest appearance

    Given I see the Search for Treatment Cost screen
    When I type at least 1 character
    And then click another element
    Then the autosuggestion dropdown will disappear

  Scenario: Select Autosuggest option

    Given I see the Search for Treatment Cost screen
    When I type at least 1 character
    Then the autosuggestion dropdown will appear
    When I click a treatment suggestion
    Then the autosuggestion dropdown will disappear
    And the treatment persists

  Scenario: Select Autosuggest option

    Given I see the Search for Treatment Cost screen
    When I type a treatment not in the dropdown
    And search for that option
    Then I will see an error message