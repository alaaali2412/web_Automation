@filterOnlineCashback
Feature: filter Online Cashback offers by category

  Scenario Outline: user should be able to filter Online Cashback offers
    Given browser opened,portal opened
    When user logged in "<email>" and "<pass>"
    When user click OnlineCashback and select "<category>"
    Then verify that user can filter

    Examples:
      | email           | pass   | category |
      | zpvdsusnjohdgixdpe@gmail.com | AYla@2412 | Beauty / Health / Cosmetics |