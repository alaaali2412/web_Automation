@facebookLogin

  Feature: first test

    Scenario: Enter home and change language
      Given open browser
      When I enter the home, I close the registration pop up
      Then go to change language and select English
      Then login with facebook



