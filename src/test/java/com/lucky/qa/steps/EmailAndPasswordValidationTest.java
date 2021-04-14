package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.utilities.LanguageReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmailAndPasswordValidationTest {
    @Given("login page opened")
    public void login_page_opened() {
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
    }

    @When("user add invalid email or password format")
    public void userAddInvalidEmailOrPasswordFormat() {
        PageGenerator.getInstance(LoginPage.class).addInvalidEmailFormat();
        PageGenerator.getInstance(LoginPage.class).checkInvalidEmailErrorMessage(LanguageReader.detectLanguage("InValidEmailErrorMsg"));
        PageGenerator.getInstance(LoginPage.class).loginWithInvalidPass();
    }

    @Then("error displayed")
    public void error_displayed() {
        PageGenerator.getInstance(LoginPage.class).checkErrorMessageIsDisplayed(LanguageReader.detectLanguage("InvalidPassErrorMsg"));
    }
}
