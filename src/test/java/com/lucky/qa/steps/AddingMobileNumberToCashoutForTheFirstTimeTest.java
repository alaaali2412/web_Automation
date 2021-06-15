package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddingMobileNumberToCashoutForTheFirstTimeTest {

    @Given("portal open in {string}new user logged in")
    public void portalOpenInNewUserLoggedIn(String language) {
        PageGenerator.getInstance(WalletPage.class).resetMobileNumberInDataBase("GoogleEmail", language);
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

    @When("add mobile Number and click continue button")
    public void add_mobile_number_and_click_continue_button() {
        PageGenerator.getInstance(WalletPage.class).addMobileNumber();
    }

    @When("get the OTP from DB according to {string} then and add it")
    public void get_the_otp_from_db_according_to_then_and_add_it(String language) {
        PageGenerator.getInstance(WalletPage.class).addOTPCode("GoogleEmail", language);
    }

    @Then("success message displayed according to portal {string}")
    public void success_message_displayed_according_to_portal(String language) {
        PageGenerator.getInstance(HomePage.class).clickWallet();
        PageGenerator.getInstance(WalletPage.class).clickRequestCashoutBtn();
        PageGenerator.getInstance(WalletPage.class).checkThatSuccessDisplayed();
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
    }
}
