@GoogleLogin
Feature: Login with user google account

  @EgyptSmokeTest
    @MoroccoSmokeTest
  Scenario Outline: user try to login via google
    Given browser open in "<language>",navigate to the portal
    When close the pop up click login with google
    And in the opened popup add "<gmail>" and "<pass>", click login
    Then verify that user login can login via google

    Examples:
      | language     | gmail                     | pass      |
      | Arabic_Egypt | qatestlucky2021@gmail.com | LUCky@abc |
