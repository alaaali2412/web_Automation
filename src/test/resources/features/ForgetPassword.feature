@ForgetPassword
Feature: User can login with wrong credentials, so he will reset his password

  Background:
    Given browser and portal open
    When  user click login

  Scenario: user can not login with wrong email or password
    Given user add Email and wrong password and click login
    When validation message displayed


  Scenario: user will reset his password to login
    Given click forget password
    When user will add his email and click Email me
    When user will open his email account and open the received email
    When user click reset password and add the new password and confirm password
    Then user can log with the new password