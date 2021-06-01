@SubscribeToNewsLetter
Feature: user can subscribe to news letter to receive emails with new offer

  Scenario Outline: user can subscribe to news letter by adding registered and anonymous email
    Given home page opened in "<language>"
    When user add registered email to subscribe news letter
    And  success message will be displayed according to "<language>"
    When user add anonymous email to subscribe news letter
    Then user get successful message and verification email
    Examples:
      | language       |
      | Arabic_Egypt   |
      | Arabic_Morocco |
      | English        |
      | French         |
