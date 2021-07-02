@BankAccountFieldsValidation
Feature: perform cashback out request

  @SmokeTest
  Scenario: logged in user should be able to Cashout if cashback > 100 EGP
    Given portal open in  "<language>" user already logged in
    When wallet page opens
    And assert that user has cashback amount that allow im to cashout
    And click cashback request button
    And choose the cashout "<method>" and add the "<cashoutAmount>"
    And user gets message "<language>" that the Cashout done successfully
    Then verify that "<cashoutAmount>" deducted from total balance and cashback
    And transaction reflect in wallet transaction list according to cashout "<method>" and "<language>"
    And transaction status "<language>" changed after approved