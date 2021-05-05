@FilterInStoreOffersByCategoroies
Feature: filter In_store offers by it`s categories

  Scenario Outline: User should be able to filter In_store offers by it`s categories
    Given browser open,portal,open In_store offer screen
    When user choose specfic "<mainCategory>" from right menu, choose "<mainSubCategory>", "<subCategory>" if required
    Then in-Store offers filtered according to the selected category

    Examples:
      | mainCategory   | mainSubCategory | subCategory |
      | أغذية ومشروبات | الوجبات السريعة | بيتزا       |
      | صحة و جمال     | صحة             | عيادات طبية |

