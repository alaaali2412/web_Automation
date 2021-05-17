@SubscribeToNewsLetter
Feature: user can subscribe to news letter to receive emails with new offers

  Background:
    Given home page opened

  Scenario: user can subscribe to news letter by adding registered email
    Given user add registered email to subscribe news letter
    Then  success message will be displayed

  Scenario: user can subscribe to news letter by adding anonymous email
    Given user add anonymous email to subscribe news letter
    Then user get successful message and verification email