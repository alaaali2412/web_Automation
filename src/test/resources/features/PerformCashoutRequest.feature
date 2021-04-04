@PerformCashoutRequest
Feature: perform cashback out request

  Scenario Outline: logged in user should be able to Cashout if cashback > 100 EGP
    Given user already logged in
    When Wallet page opens
    When Assert that user has cashback amount that allow im to cashout
    And Click cashback request button
    And Choose the cashout "<method>" and add the "<cashoutAmount>"
    And User gets message that the Cashout done successfully
    Then Verify that "<cashoutAmount>" deducted from total balance and cashback
    Then transaction reflect in wallet transaction list according to cashout "<method>"

    Examples:
      | method       | cashoutAmount |
      | Bank Account | 20            |
      | Aman         | 20            |
