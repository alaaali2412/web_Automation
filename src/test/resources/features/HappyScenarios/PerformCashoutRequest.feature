@PerformCashoutRequest
Feature: perform cashback out request

  @SmokeTest
  Scenario Outline: logged in user should be able to Cashout if cashback > 100 EGP
    Given portal open in  "<language>" user already logged in
    When Wallet page opens
    And Assert that user has cashback amount that allow him to cashout
    And Click cashback request button
    And Choose the cashout "<method>" and add the "<cashoutAmount>"
    And User gets message "<language>" that the Cashout done successfully
    Then Verify that "<cashoutAmount>" deducted from total balance and cashback
    And transaction reflect in wallet transaction list according to cashout "<method>" and "<language>"
    And transaction status "<language>" changed after approved

    Examples:
      | language | method | cashoutAmount |
      | English  | Aman   | 20            |


