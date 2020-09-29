@emailLogin

  Feature: Enter to home and login

    Scenario Outline: Enter home and change language
      Given open browser
      When I enter the home, I close the registration pop up
      When go to change language and select English
      When login with "<email>" and "<password>" registered
      Then verify the login

      Examples:
        | email           | password   |
        | qa@thelucky.app | Qa1234567& |







