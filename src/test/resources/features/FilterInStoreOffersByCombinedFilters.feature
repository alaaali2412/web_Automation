@FilterInStoreOffersByCombinedFilters
Feature: filter In_store offers by it`s categories & Locations

  Scenario Outline: filtering In Store offers by location and category at the same time
    Given browser & portal opened,  open In_store offer screen
    When user filter by location location "<Location>", "<subLocation>"
    When user choose specific "<mainCategory>",  "<mainSubCategory>", "<subCategory>" if required
    When combine between both filters then click apply

    Examples:
      | mainCategory | mainSubCategory | subCategory             | Location   | subLocation |

      | Fashion      | Women           | Jewellery & Accessories | Alexandria | San Stefano  |
