package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;

public class PerformCashoutRequestTest {
    ArrayList<Double> valuesBeforeCashout;

    @Given("portal open in  {string} user already logged in")
    public void portalOpenInUserAlreadyLoggedIn(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);

        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login("LoginData.properties"
                , "Email", "Password");
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
        PageGenerator.getInstance(WalletPage.class).clearCashoutTransactionsInDB(language, "Email");
    }

    @When("wallet page opens")
    public void Wallet_page_opens() {
        PageGenerator.getInstance(HomePage.class).clickWallet();
    }

    @When("assert that user has cashback amount that allow im to cashout")
    public void assertThatUserHasCashbackAmountThatAllowImToCashout() throws InterruptedException {
        valuesBeforeCashout = PageGenerator.getInstance(WalletPage.class).userBalanceBeforeCashout();
    }

    @When("click cashback request button")
    public void click_cashback_request_button() {
        PageGenerator.getInstance(WalletPage.class).clickRequestCashoutBtn();
    }

    @And("choose the cashout {string} and add the {string}")
    public void chooseTheCashoutAndAddThe(String method, String cashoutAmount) {
        PageGenerator.getInstance(WalletPage.class).cashoutMethod(method, cashoutAmount);
        PageGenerator.getInstance(WalletPage.class).clickContinueBtn();
    }

    @And("user gets message {string} that the Cashout done successfully")
    public void userGetsMessageThatTheCashoutDoneSuccessfully(String language) {
        PageGenerator.getInstance(WalletPage.class).checkCashoutSuccessMessage(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "CashoutSuccessMessage"));
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickWallet();
    }

    @Then("verify that {string} deducted from total balance and cashback")
    public void verifyThatDeductedFromTotalBalanceAndCashback(String cashoutAmount) throws InterruptedException {
        Double totalBalanceAfter = valuesBeforeCashout.get(0) - Double.parseDouble(cashoutAmount);
        Double totalCashbackAfter = valuesBeforeCashout.get(1) - Double.parseDouble(cashoutAmount);
        Assert.assertEquals(PageGenerator.getInstance(WalletPage.class).getUserTotalBalance(), totalBalanceAfter);
        Assert.assertEquals(PageGenerator.getInstance(WalletPage.class).getUserCashbackBalance(), totalCashbackAfter);
    }

    @Then("transaction reflect in wallet transaction list according to cashout {string} and {string}")
    public void TransactionReflectInWalletTransactionListAccordingToCashoutAnd(String method, String language) {
        PageGenerator.getInstance(WalletPage.class).checkIfTransactionReflected(method,
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "transactionNameLanguage"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "TransactionStatusLanguage"));
    }

    @And("transaction status {string} changed after approved")
    public void TransactionStatusChangedAfterApproved(String language) {
        PageGenerator.getInstance(WalletPage.class).approveTheCashoutTransaction(language, "Email");
        PageGenerator.getInstance(WalletPage.class).checkTheCashoutTransactionApproved(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "ApprovedCashoutTransactionstatus"));
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
    }
}
