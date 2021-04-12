@EmailAndPasswordValidation
Feature: Wrong Email And Password Format

  Scenario: User can not login or register with invalid Email or password
    Given login page opened
    When user add invalid email or password format
    Then error displayed



