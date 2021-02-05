package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class performCashoutRequestByBankAccount {
    WebDriver driver = Hook.getDriver();
    Double cashoutAmount;
    Double balance;

    @Given("browser, portal opened,user login {string} and {string}")
    public void browserPortalOpenedUserLoginAnd(String email, String pass) {
        PageGenerator.getInstance(HomePage.class, driver).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class, driver).login(email, pass);

    }

    @When("wallet opens and cashback balance >= {double} EGP")
    public void wallet_opens_and_cashback_balance_egp(Double amount) throws InterruptedException {
        PageGenerator.getInstance(HomePage.class, driver).clickWallet();
        balance = PageGenerator.getInstance(WalletPage.class, driver).getUserTotalBalance();
        PageGenerator.getInstance(WalletPage.class, driver).getUserCashbackBalance(amount);
    }

    @And("click request Cashout and choose Bank Account add {string}")
    public void clickRequestCashoutAndChooseBankAccountAdd(String CashoutAmount) {
        cashoutAmount = PageGenerator.getInstance(WalletPage.class, driver).addAmountToCashoutByBankAccount(CashoutAmount);

    }

    @And("add all the mandatory fields and click continue")
    public void addAllTheMandatoryFieldsAndClickContinue()  {
        PageGenerator.getInstance(WalletPage.class, driver).addBankAccountFields();
        PageGenerator.getInstance(WalletPage.class, driver).clickContinueBtn();
    }

    @And("user get that the Cashout done successfully")
    public void userGetThatTheCashoutDoneSuccessfully() {
        PageGenerator.getInstance(WalletPage.class, driver).checkCashoutSuccessMessage();
    }

    @Then("verify that cashout amount deducted from user balance and cashback")
    public void verifyThatCashoutAmountDeductedFromUserBalanceAndCashback() throws InterruptedException {

        PageGenerator.getInstance(HomePage.class, driver).clickWallet();
        Double balanceAfter = balance - cashoutAmount;
        Assert.assertEquals(PageGenerator.getInstance(WalletPage.class, driver).getUserTotalBalance(), balanceAfter);


    }
}
