@emailLogin

  Feature: first test

    Scenario: Enter home and change language
      Given open browser
      When I enter the home, I close the registration pop up
      When go to change language and select English
      When login with email registered
      Then verify the login






