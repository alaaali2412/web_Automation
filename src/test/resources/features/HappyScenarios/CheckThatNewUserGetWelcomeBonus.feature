@CheckThatNewUserGetWelcomeBonus
Feature: Check that user will receive welcome bouns in wallet after registration

  @SmokeTest
  Scenario Outline: user register and and activate his email
    Given portal open in "<language>", login with new user
    When open the wallet screen
    Then welcome bonus transaction "<language>" in wallet screen

    Examples:
      | language |
      | English  |
