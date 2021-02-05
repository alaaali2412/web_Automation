@facebookLogin
Feature: Login with user facebook account

  Scenario Outline: User try to login via facebook
    Given browser open,navigate to portal URl
    When close the pop up click login with facebook
    And in the opened popup add facebook "<email>" and "<pass>", click login
    Then verify that user login can login via facebook

    Examples:
      | email           | pass   |
      | qatestluckyfb2021@gmail.com | LUcky@2123 |