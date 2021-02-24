package com.lucky.qa.steps;

import com.lucky.qa.connectors.SharedDriver;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class performCashoutRequestByAmanTest {
    public performCashoutRequestByAmanTest(SharedDriver driver){

    }

    Double CashoutAmount;
    Double balance;

    @Given("portal is opend and user login")
    public void portal_is_opend_and_user_login() {
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login();
    }


    @When("wallet page opens")
    public void walletPageOpens() throws InterruptedException {
        PageGenerator.getInstance(HomePage.class).clickWallet();
        balance = PageGenerator.getInstance(WalletPage.class).getUserTotalBalance();
    }

    @When("if cashabck >= {double} EGP click cashback request else print not enough balance")
    public void if_cashabck_egp_click_cashback_request_else_print_not_enough_balance(Double amount) {
        PageGenerator.getInstance(WalletPage.class).getUserCashbackBalance(amount);

    }

    @And("click aman add an {string} click continue")
    public void clickAmanAddAnClickContinue(String cashoutAmount) {

        PageGenerator.getInstance(WalletPage.class).clickAman();
        CashoutAmount = PageGenerator.getInstance(WalletPage.class).addAmountToCashoutByAman(cashoutAmount);
        PageGenerator.getInstance(WalletPage.class).clickContinueBtn();
    }

    @And("user gets that the Cashout done successfully")
    public void userGetsThatTheCashoutDoneSuccessfully() {
        PageGenerator.getInstance(WalletPage.class).checkCashoutSuccessMessage();
    }

    @Then("verify that cashout amount deducted from balance and cashback")
    public void verifyThatCashoutAmountDeductedFromBalanceAndCashback() throws InterruptedException {
        PageGenerator.getInstance(HomePage.class).clickWallet();
        Double balanceAfter = balance - CashoutAmount;
        Assert.assertEquals(balanceAfter, PageGenerator.getInstance(WalletPage.class).getUserTotalBalance());

    }
}
