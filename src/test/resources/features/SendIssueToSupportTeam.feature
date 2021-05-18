@SendIssueToSupportTeam
Feature: user can send the issue he face to the support team

  Scenario: user can open contact us page and see support email
    Given home page open
    When user click on contact us
    Then user can text message that explain to user how to contact support team
