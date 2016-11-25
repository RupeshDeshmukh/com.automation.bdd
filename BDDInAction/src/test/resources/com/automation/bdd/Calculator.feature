@requires_browser
Feature: Calculator operation using Google search

  # Simple scenario to test addition operation using google search.
  Scenario: Addition using google search
    Given A Google search page
    When I enter the search term "2+2"
    And I submit the search by pressing "enter"
    Then I should get result "4"

  # Simple scenario to test substraction operation using google search.
  Scenario: Addition using google search
    Given A Google search page
    When I enter the search term "10-5"
    And I submit the search by pressing "enter"
    Then I should get result "5"
