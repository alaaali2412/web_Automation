@performCashoutRequestByBankAccount

Feature: perform Cashout Request By Bank Account

  Scenario Outline: the loggedin user should be able to Cashout if cashback > 100 EGP
    Given browser, portal opened,user login
    When wallet opens and cashback balance >= 100.00 EGP
    And click request Cashout and choose Bank Account add "<CashoutAmount>"
    And add all the mandatory fields and click continue
    And user get that the Cashout done successfully
    Then verify that cashout amount deducted from user balance and cashback

    Examples:
      | CashoutAmount |
      | 20            |