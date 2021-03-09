@Registration
Feature: New user create new account on affiliate

  Background: Email and Password not registered before
    Given browser, portal open

  Scenario: user can not login with verify the email
    Given user click signup button
    When user add all the mandatory fields of Registration
    When click register button
    When try to login directly with same email and password
    Then user can not login without verifying the email

  Scenario: user can login after verifying the email
    Given user open his email account, open the received email from lucky
    When verify the registered email
    Then user can login with the new email