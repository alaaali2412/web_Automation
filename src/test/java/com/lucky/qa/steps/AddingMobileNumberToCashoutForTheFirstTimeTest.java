package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddingMobileNumberToCashoutForTheFirstTimeTest {

    @Given("portal open in {string}new user logged in")
    public void portalOpenInNewUserLoggedIn(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login("LoginData.properties"
                , "GoogleEmail", "NewPassword");
    }

    @When("Wallet page opens, click cashout button")
    public void wallet_page_opens_click_cashout_button() {
        PageGenerator.getInstance(HomePage.class).clickWallet();
        PageGenerator.getInstance(WalletPage.class).clickRequestCashoutBtn();
    }

    @And("add {string} and click continue button")
    public void addAndClickContinueButton(String mobileNumber) throws InterruptedException {
        PageGenerator.getInstance(WalletPage.class).addMobileNumber(mobileNumber);
    }

    @And("get the OTP from DB and add it")
    public void getTheOTPFromDBAndAddIt() {
        PageGenerator.getInstance(WalletPage.class).addOTPCode("GoogleEmail");
    }

    @Then("success message displayed")
    public void successMessageDisplayed() {
        PageGenerator.getInstance(HomePage.class).clickWallet();
        PageGenerator.getInstance(WalletPage.class).clickRequestCashoutBtn();
        PageGenerator.getInstance(WalletPage.class).checkThatSuccessDisplayed();
        PageGenerator.getInstance(WalletPage.class).resetMobileNumberInDataBase("GoogleEmail");
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
    }
}
