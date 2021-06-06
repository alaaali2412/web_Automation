@AddWrongOtpWhenVerifyingMobileNumberInCashout
Feature: add wrong OTP when verifying mobile number in cashout

  Scenario Outline: User add wrong OTP when verifying mobile number in cashout
    Given portal opens in "<language>",user logged in
    When open wallet screen, click cashout button
    And add "<MobileNumber>" , click continue button
    And add wrong OTP then click continue
    Then error message displayed according to portal "<language>"
    Examples:
      | language     | MobileNumber |
      | Arabic_Egypt | 01315525888  |
