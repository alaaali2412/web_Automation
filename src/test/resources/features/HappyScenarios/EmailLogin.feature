@EmailLogin
Feature: Login with Email, password
  @SmokeTest
  Scenario Outline: User try to login via Email,Password
    Given browser open "<language>",navigate to portal
    When close the pop up click on login button
    And login with valid email and pass
    Then verify the login
    Examples:
      | language       |
      | Arabic_Egypt   |
      | English        |
      | Arabic_Morocco |
      | French         |