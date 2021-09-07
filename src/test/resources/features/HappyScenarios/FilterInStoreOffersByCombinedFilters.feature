@FilterInStoreOffersByCombinedFilters
Feature: filter In_store offers by it`s categories & Locations

  @EgyptSmokeTest
  Scenario Outline: filtering In Store offers by location and category at the same time
    Given browser & portal opened in "<language>",  open In_store offer screen
    When user filter by location location "<Location>", "<subLocation>"
    When user choose specific "<mainCategory>",  "<mainSubCategory>", "<subCategory>" if required
    When combine between both filters then click apply

    Examples:
      | language     | mainCategory | mainSubCategory | subCategory             | Location   | subLocation |
      | English      | Electronics  | Electronics     | Computers & Accessories | Cairo      | Heliopolis  |
      | Arabic_Egypt | صحة و جمال   | صحة             | عيادات طبية             | الإسكندرية | ستانلي      |