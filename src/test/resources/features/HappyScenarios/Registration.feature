@Registration
Feature: New user create new account on affiliate
  @SmokeTest
  Scenario Outline: user can not login until verify the newly registered email
    Given portal open in "<language>" user click signup button
    When user add all the mandatory fields of Registration
    When click register button
    When try to login directly with same email and password
    Then user can not login without verifying the email "<language>"
    When user open his email account, open the received email from lucky
    When verify the registered email
    Then user can login with the new email

    Examples:
      | language     |
      | English  |
