@FieldsValidationDuringRegistration

Feature: Fields Validation During Registration

  Scenario: user can not register with invalid values
    Given Registration page opened
    When add invalid values in registration fields
    Then error messages displayed at the fields that contain invalid values


