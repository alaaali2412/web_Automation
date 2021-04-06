@SendIssueToSupportTeam
Feature: user can send the issue he face to the support team

  Background:
    Given user open contact us screen

  Scenario: user can not send issue without filling all the mandatory fields
    Given Leave all the fields empty
    When Click send
    Then Validation messages displayed at the mandatory fields

  Scenario Outline: user can communicate with the support team send the issue he faced
    When fill all the mandatory fields "<Name>" , "<MobilNumber>", "<Text>"
    When click send button
    Then user get successful message
    Examples:
      | Name   | MobilNumber | Text |
      | testQA | 01234567890 | test |
