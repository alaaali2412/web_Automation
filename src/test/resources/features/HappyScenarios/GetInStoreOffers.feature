@GetInStoreOffers
Feature: Get in store offers

  @EgyptSmokeTest
  Scenario Outline: user try to get in store offers
    Given browser open,open portal in "<language>", user login
    When click on instore offers
    And select an offer and click it
    And click get offer
    Then popup displayed in "<language>" that user should download lucky app
    Examples:
      | language     |
      | Arabic_Egypt |