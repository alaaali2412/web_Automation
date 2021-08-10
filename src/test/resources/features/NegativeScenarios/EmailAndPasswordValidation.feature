@EmailAndPasswordValidation
Feature: Wrong Email And Password Format

  Scenario Outline: user can not login or register with invalid Email or password
    Given portal "<language>",  login page opened
    When user add invalid email or password format according to "<language>"
    Then error displayed according to "<language>"
    Examples:
      | language       |
      | English        |

