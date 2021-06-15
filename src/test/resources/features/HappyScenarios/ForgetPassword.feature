@ForgetPassword
Feature: User can login with wrong credentials, so he will reset his password

  @SmokeTest
  Scenario Outline: user can reset his password
    Given portal "<language>", user click login
    When user add Email and wrong password and click login
    When validation message displayed according to "<language>",
    When click forget password
    When user will add his email and click Email me
    When user will open his email account and open the received email
    When user click reset password and add the new password and confirm password
    Then user can log with the new password
    Examples:
      | language     |
      | Arabic_Egypt |