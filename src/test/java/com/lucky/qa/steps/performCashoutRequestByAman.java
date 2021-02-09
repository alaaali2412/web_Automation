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

public class performCashoutRequestByAman {
    WebDriver driver = Hook.getDriver();
    Double CashoutAmount;
    Double balance;

    @Given("portal is opend and user loged in {string}, {string}")
    public void portalIsOpendAndUserLogedIn(String email, String pass) {
        PageGenerator.getInstance(HomePage.class, driver).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class, driver).login(email, pass);

    }

    @When("wallet page opens")
    public void walletPageOpens() throws InterruptedException {
        PageGenerator.getInstance(HomePage.class, driver).clickWallet();
        balance = PageGenerator.getInstance(WalletPage.class, driver).getUserTotalBalance();
    }

    @When("if cashabck >= {double} EGP click cashback request else print not enough balance")
    public void if_cashabck_egp_click_cashback_request_else_print_not_enough_balance(Double amount) {
        PageGenerator.getInstance(WalletPage.class, driver).getUserCashbackBalance(amount);

    }

    @And("click aman add an {string} click continue")
    public void clickAmanAddAnClickContinue(String cashoutAmount) {

        PageGenerator.getInstance(WalletPage.class, driver).clickAman();
        CashoutAmount = PageGenerator.getInstance(WalletPage.class, driver).addAmountToCashoutByAman(cashoutAmount);
        PageGenerator.getInstance(WalletPage.class, driver).clickContinueBtn();
    }

    @And("user gets that the Cashout done successfully")
    public void userGetsThatTheCashoutDoneSuccessfully() {
        PageGenerator.getInstance(WalletPage.class, driver).checkCashoutSuccessMessage();
    }

    @Then("verify that cashout amount deducted from balance and cashback")
    public void verifyThatCashoutAmountDeductedFromBalanceAndCashback() throws InterruptedException {
        PageGenerator.getInstance(HomePage.class, driver).clickWallet();
        Double balanceAfter = balance - CashoutAmount;
        Assert.assertEquals(balanceAfter, PageGenerator.getInstance(WalletPage.class, driver).getUserTotalBalance());

    }
}
