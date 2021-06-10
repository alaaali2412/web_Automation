@FilterOnlineCashback
Feature: filter Online Cashback offers by category
  @SmokeTest
  Scenario Outline: user should be able to filter Online Cashback offers
    Given browser opened,portal opened in "<language>"
    When user click OnlineCashback and select "<category>"
    Then verify filter with "<category>" with backend according to "<language>"

    Examples:
      | language       | category         |
      | Arabic_Egypt   | السفر و الرفاهية |
      | Arabic_Morocco | مودا             |
      | English        | Automotive       |
      | French         | Mode             |