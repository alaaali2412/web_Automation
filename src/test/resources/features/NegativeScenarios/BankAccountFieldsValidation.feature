@CheckBankAccountFieldsValidations
Feature: check that error message will be displayed in case user add incorrect values

  Scenario Outline: user add invalid valued in while adding bank account detials
    Given portal open in  "<language>" user already logged in
    When open wallet screen, click cashout button
    And choose bank account as cashout method
    Then bank account Fields displayed, add the amount
    But user can not leave fields empty, error message displayed "<language>"
    But user can not add arabic values, error message displayed "<language>"
    But user must add IBAN in the correct format otherwise, error message displayed "<language>"

    Examples:
      | language     |
      | English      |
      | Arabic_Egypt |