@filterOnlineCashback
Feature: filter Online Cashback offers by category

  Scenario Outline: user should be able to filter Online Cashback offers
    Given browser opened,portal opened
    When user logged in
    When user click OnlineCashback and select "<category>"
    Then verify that user can filter

    Examples:
      | category                    |
      | Beauty / Health / Cosmetics |
      | Fashion                     |