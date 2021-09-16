package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddWrongOtpWhenVerifyingMobileNumberInCashoutTest {

    @Given("portal opens in {string},user logged in")
    public void portal_opens_in_user_logged_in(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login("LoginData.properties"
                , "GoogleEmail", "NewPassword");
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
    }

    @When("open wallet screen, click cashout button")
    public void open_wallet_screen_click_cashout_button() {
        PageGenerator.getInstance(HomePage.class).clickWallet();
        PageGenerator.getInstance(WalletPage.class).clickRequestCashoutBtn();
    }

    @When("add mobile number, click continue button")
    public void add_mobile_number_click_continue_button() {
        PageGenerator.getInstance(WalletPage.class).addMobileNumber();
    }

    @When("add wrong OTP then click continue")
    public void add_wrong_otp_then_click_continue() {
        PageGenerator.getInstance(WalletPage.class).addWrongOTP();
    }

    @Then("error message displayed according to portal {string}")
    public void error_message_displayed_according_to_portal(String language) {
        PageGenerator.getInstance(WalletPage.class).assertOtpErrorMessage(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "OTPErrorMessage"));
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
        PageGenerator.getInstance(WalletPage.class).resetMobileNumberInDataBase("RegistrationEmail", language);
    }

}
