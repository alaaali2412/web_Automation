package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmailAndPasswordValidationTest {

    @Given("portal {string},  login page opened")
    public void portalLoginPageOpened(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
    }

    @When("user add invalid email or password format according to {string}")
    public void userAddInvalidEmailOrPasswordFormatAccordingTo(String language) {
        PageGenerator.getInstance(LoginPage.class).addInvalidEmailFormat();
        PageGenerator.getInstance(LoginPage.class).checkInvalidEmailErrorMessage(PageGenerator.
                getInstance(BasePage.class).detectLanguage(language, "InValidEmailErrorMsg"));
        PageGenerator.getInstance(LoginPage.class).loginWithInvalidPass();
    }

    @Then("error displayed according to {string}")
    public void errorDisplayedAccordingTo(String language) {
        PageGenerator.getInstance(LoginPage.class).checkErrorMessageIsDisplayed(PageGenerator.
                getInstance(BasePage.class).detectLanguage(language, "InvalidPassErrorMsg"));
    }
}
