@SendIssueToSupportTeam
Feature: user can send the issue he face to the support team

  @EgyptSmokeTest
    @MoroccoSmokeTest
  Scenario Outline: user can open contact us page and see support email
    Given home page open in "<language>"
    When user click on contact us
    Then user can text message according to "<language>"that explain to user how to contact support team
    Examples:
      | language     |
      | Arabic_Egypt |
      | English      |