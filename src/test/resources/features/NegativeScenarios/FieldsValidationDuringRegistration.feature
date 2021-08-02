@FieldsValidationDuringRegistration

Feature: Fields Validation During Registration
  @SmokeTest
  Scenario Outline: user can not register with invalid values
    Given portal "<Language>" Registration page opened
    When add invalid values "<Language>"in registration fields
    Then error messages displayed at the fields that contain invalid values according to "<Language>"
    Examples:
      | Language       |
      | English        |
