@searchOnlineCashbackOffers
Feature: search Online Cashback Offers

  Scenario Outline: search Online Cashback Offers by store name
    Given browser, portal opened
    When add "<keyword>" in search field and click enter
    Then verify the search result with the backend

    Examples:
      | keyword |
      | amazon  |