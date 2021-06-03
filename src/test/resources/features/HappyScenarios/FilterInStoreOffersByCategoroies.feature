@FilterInStoreOffersByCategoroies
Feature: filter In_store offers by it`s categories

  Scenario Outline: User should be able to filter In_store offers by it`s categories
    Given browser open,portal,open "<language>" In_store offer screen
    When user choose specfic "<mainCategory>" from right menu, choose "<mainSubCategory>", "<subCategory>" if required
    Then in-Store offers filtered according to the selected category

    Examples:
      | language     | mainCategory   | mainSubCategory | subCategory |
      | Arabic_Egypt | أغذية ومشروبات | الوجبات السريعة | بيتزا       |
      | Arabic_Egypt | صحة و جمال     | صحة             | عيادات طبية |
      | English      | Fashion        | Women           | Clothes     |
