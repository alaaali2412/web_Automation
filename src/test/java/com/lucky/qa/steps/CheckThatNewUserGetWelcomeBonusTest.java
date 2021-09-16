package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.WalletPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckThatNewUserGetWelcomeBonusTest {

    @Given("portal open in {string}, login with new user")
    public void portal_open_in_login_with_new_user(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login("RegistrationData.properties"
                , "RegistrationEmail", "RegistrationPassword");
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
    }

    @When("open the wallet screen")
    public void open_the_wallet_screen() {
        PageGenerator.getInstance(HomePage.class).clickWallet();
    }

    @Then("welcome bonus transaction {string} in wallet screen")
    public void welcome_bonus_transaction_in_wallet_screen(String language) {
        PageGenerator.getInstance(WalletPage.class).checkWelcomeBonus(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "WelcomeBonusTransactionName"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "TransactionStatusLanguage"));
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
    }
}
