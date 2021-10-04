Feature: All documents are available

  Scenario: Knowledge Base
    Given browse to url
    When log in Admin page with name and password
    Then click hamburger menu
    And click Documentation
    Then open Knowledge Base
    And verify Knowledge Base page was loaded in tab 1