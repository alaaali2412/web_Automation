package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class PerformCashoutRequestByBankAccountTest {
    Double cashoutAmount;
    Double balance;

    @Given("browser, portal opened,user login")
    public void browser_portal_openeduser_login() {
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login("LoginData.properties",
                "Email", "password");
    }

    @When("wallet opens and cashback balance >= {double} EGP")
    public void wallet_opens_and_cashback_balance_egp(Double amount) throws InterruptedException {
        PageGenerator.getInstance(HomePage.class).clickWallet();
        balance = PageGenerator.getInstance(WalletPage.class).getUserTotalBalance();
        PageGenerator.getInstance(WalletPage.class).getUserCashbackBalance(amount);
    }

    @And("click request Cashout and choose Bank Account add {string}")
    public void clickRequestCashoutAndChooseBankAccountAdd(String CashoutAmount) {
        cashoutAmount = PageGenerator.getInstance(WalletPage.class).addAmountToCashoutByBankAccount(CashoutAmount);

    }

    @And("add all the mandatory fields and click continue")
    public void addAllTheMandatoryFieldsAndClickContinue() {
        PageGenerator.getInstance(WalletPage.class).addBankAccountFields();
        PageGenerator.getInstance(WalletPage.class).clickContinueBtn();
    }

    @And("user get that the Cashout done successfully")
    public void userGetThatTheCashoutDoneSuccessfully() {
        PageGenerator.getInstance(WalletPage.class).checkCashoutSuccessMessage();
    }

    @Then("verify that cashout amount deducted from user balance and cashback")
    public void verifyThatCashoutAmountDeductedFromUserBalanceAndCashback() throws InterruptedException {

        PageGenerator.getInstance(HomePage.class).clickWallet();
        Double balanceAfter = balance - cashoutAmount;
        Assert.assertEquals(PageGenerator.getInstance(WalletPage.class).getUserTotalBalance(), balanceAfter);
    }
}
