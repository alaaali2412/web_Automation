@performCashoutRequestByAman
Feature: perform Cashout Request By Aman

  Scenario Outline: the logged in user should be able to Cashout his cashback if it more than 100 EGP
    Given browser, portal open
    When user login "<email>" and "<pass>"
    And open tha wallet and make sure that he has more than 100 EGP in his cashback balance
    And click request Cashout and choose Aman
    And add the amount and click continue
    And user get that Cashout successful
    Then vrify that cashout deducted from user balance and cashback

    Examples:
      | email           | pass   |
      | zpvdsusnjohdgixdpe@gmail.com | AYla@2412 |