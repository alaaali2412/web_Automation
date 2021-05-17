@SearchOnlineCashbackOffers
Feature: search Online Cashback Offers

  Scenario Outline: search Online Cashback Offers by store name
    Given browser, portal opened in "<language>"
    When add "<keyword>" in search field and click enter
    Then verify result with "<keyword>" with the backend according to "<languageValue>"

    Examples:
      | language | languageValue | keyword |
      | Arabic   | 2             | سوق     |
      | English  | 1             | amazon  |