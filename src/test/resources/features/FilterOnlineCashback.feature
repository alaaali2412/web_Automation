@FilterOnlineCashback
Feature: filter Online Cashback offers by category

  Scenario Outline: user should be able to filter Online Cashback offers
    Given browser opened,portal opened in "<language>"
    When user click OnlineCashback and select "<category>"
    Then verify filter with "<category>" with backend according to "<languageValue>"

    Examples:
      | language | languageValue | category         |
      | Arabic   | 2             | الالعاب          |
      | Arabic   | 2             | السفر و الرفاهية |
      | English  | 1             | Automotive       |