@performCashoutRequestByBankAccount

Feature: perform Cashout Request By Bank Account

  Scenario Outline: the loggedin user should be able to Cashout if cashback > 100 EGP
    Given browser, portal opened,user login "<email>" and "<pass>"
    When wallet opens and cashback balance > 100 EGP
    And click request Cashout and choose Bank Account
    And add all the mandatory fields and click continue
    And user get that the Cashout done successfully
    Then vrify that cashout amount deducted from user balance and cashback

    Examples:
      | email           | pass   |
      | zpvdsusnjohdgixdpe@gmail.com | AYla@2412 |