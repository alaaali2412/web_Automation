package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckBankAccountFieldsValidationsTest {

    @When("choose bank account as cashout method")
    public void choose_bank_account_as_cashout_method() {
        PageGenerator.getInstance(WalletPage.class).clickBankAccountBtn();
    }

    @Then("bank account Fields displayed, add the amount")
    public void bank_account_fields_displayed_add_the_amount() {
        PageGenerator.getInstance(WalletPage.class).addAmountToCashoutByBankAccount("20");
    }

    @Then("user can not leave fields empty, error message displayed {string}")
    public void user_can_not_leave_fields_empty_error_message_displayed(String language) {
        PageGenerator.getInstance(WalletPage.class).clickContinueBtn();
        PageGenerator.getInstance(WalletPage.class).checkBankAccountFieldsValidationMessages(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountFullNameEmptyField"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountBankNameEmptyField"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountAccountHolderAddressEmptyField"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountBranchNameEmptyField"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountBankAddressEmptyField"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountAccountNumberEmptyField"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountSWIFTCodeEmptyField"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountIBANEmptyField")
        );
    }

    @Then("user can not add arabic values, error message displayed {string}")
    public void user_can_not_add_arabic_values_error_message_displayed(String language) {
        PageGenerator.getInstance(WalletPage.class).addArabicValuesOnBankAccountFields();
        PageGenerator.getInstance(WalletPage.class).clickContinueBtn();
        PageGenerator.getInstance(WalletPage.class).checkBankAccountFieldsValidationMessages(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountFullNameArabicValue"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountBankNameArabicValued"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountAccountHolderAddressArabicValue"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountBranchNameArabicValue"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountBankAddressArabicValue"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountAccountNumberArabicValue"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountSWIFTCodeArabicValue"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountIBANArabicValue")
        );
    }

    @Then("user must add IBAN in the correct format otherwise, error message displayed {string}")
    public void user_must_add_iban_in_the_correct_format_otherwise_error_message_displayed(String language) {
        PageGenerator.getInstance(WalletPage.class).addInvalidIBANFormat();
        PageGenerator.getInstance(WalletPage.class).clickContinueBtn();
        PageGenerator.getInstance(WalletPage.class).checkIBANInvalidFormatErrorMessage(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "BankAccountIBANInvalidFormat"));
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
    }
}
