@EmailLogin
Feature: Login with Email, password

  @SmokeTest
  Scenario Outline: user try to login via Email,Password
    Given browser open "<language>",navigate to portal
    When close the pop up click on login button
    And login with valid email and pass
    Then verify the login
    Examples:
      | language     |
      | English      |
      | Arabic_Egypt |