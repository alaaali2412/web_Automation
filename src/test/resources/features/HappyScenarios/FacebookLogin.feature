@FacebookLogin
Feature: Login with user facebook account

  @ignore
  Scenario Outline: user try to login via facebook
    Given browser open,navigate to portal URl "<language>"
    When close the pop up click login with facebook
    And in the opened popup add facebook "<email>" and "<pass>", click login
    Then verify that user login can login via facebook

    Examples:
      | language | email                       | pass       |
      | English  | qatestluckyfb2021@gmail.com | LUcky@2123 |