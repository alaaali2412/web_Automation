@EmailLogin
Feature: Login with Email, password

  Scenario: User try to login via Email,Password
    Given browser open,navigate to portal
    When close the pop up click on login button
    And login with valid email and pass
    Then verify the login