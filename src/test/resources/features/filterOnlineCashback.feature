@filterOnlineCashback
Feature: Filter Online Cashback offers with specific category

  Scenario Outline: User filter with specific category from online cashback
    Given browser open,navigate to portal, login "<email>" and "<pass>"
    When click online cashback offers and select "<category>"
    Then online cashback page opens and same category selected

    Examples:
      | email           | pass   | category |
      | zpvdsusnjohdgixdpe@gmail.com | AYla@2412 | Automotive |