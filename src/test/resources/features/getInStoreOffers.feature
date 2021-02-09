@inStoreOffers
Feature: Get in store offers

  Scenario: User try to get in store offers
    Given browser open,open portal, user login
    When click on instore offers
    And select an offer and click it
    And click get offer
    Then popup displayed that userf should download lucky app