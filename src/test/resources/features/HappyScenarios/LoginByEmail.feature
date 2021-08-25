@LoginByEmail
Feature: Login with Email and password

    @EgyptSmokeTest
    @MoroccoSmokeTest
  Scenario Outline: user try to login via google
    Given browser open in "<language>",navigate to the portal
    When close the pop up then click login
    And add email and pass, click login
    Then verify that user login

    Examples:
      | language     |
      | Arabic_Egypt |
