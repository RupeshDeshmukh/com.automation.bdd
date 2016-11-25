@requires_browser
Feature: Enter a search in Google

  # Simple scenario to check page title for search result.
  Scenario Outline: Search results should display the search term in the title regardless of submission method
    Given A Google search page
    When I enter the search term "<search_term>"
    And I submit the search by pressing "<submission_method>"
    Then The search result page title should contain the "<search_term>"

    Examples: Term and submission method combinations
      | search_term | submission_method |
      | BDD         | enter             |
      | Selenium    | enter             |
