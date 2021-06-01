@FilterInStoreOffersByLocation
Feature: filter In_store offers by it`s Location

  Scenario Outline: User should be able to filter In_store offers by it`s location
    Given browser open,portal in "<language>",open In_store offer
    When user choose specfic "<Location>" from the right menu
    And select "<subLocation>" if required
    Then in-Store offers filtered according to the selected location

    Examples:
      | language     | Location   | subLocation |
      | Arabic_Egypt | الإسكندرية | سان ستيفانو |
      | Arabic_Egypt | الجيزة     | وسط البلد   |








