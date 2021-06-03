@AddingMobileNumberToCashoutForTheFirstTime
Feature: Adding Mobile Number To Cashout For The First Time

  Scenario Outline: user add mobile number for the first time while cashout
    Given portal open in "<language>"new user logged in
    When Wallet page opens, click cashout button
    And add "<mobileNumber>" and click continue button
    And get the OTP from DB and add it
    Then success message displayed
    Examples:
      | language     | mobileNumber |
      | Arabic_Egypt | 01315525888  |
