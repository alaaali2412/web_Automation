@performCashoutRequestByAman
Feature: perfoem cahback out request by Aman

  Scenario Outline: loggedin user should be able to Cashout if cashback > 100 EGP
    Given portal is opend and user loged in "<email>", "<pass>"
    When wallet page opens
    When if cashabck >= 100.00 EGP click cashback request else print not enough balance
    And click aman add an "<cashoutAmount>" click continue
    And user gets that the Cashout done successfully
    Then verify that cashout amount deducted from balance and cashback

    Examples:
      | email                 | pass      | cashoutAmount |
      | alaa.ali@thelucky.app | AYla@2412 | 30            |