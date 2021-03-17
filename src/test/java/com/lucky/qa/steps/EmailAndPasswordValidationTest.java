package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmailAndPasswordValidationTest {
    @Given("login page opened")
    public void login_page_opened() {
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
    }

    @When("user add invalid email format")
    public void userAddInvalidEmailFormat() {
        PageGenerator.getInstance(LoginPage.class).addInvalidEmailFormat();
    }

    @Then("error displayed")
    public void error_displayed() {
        PageGenerator.getInstance(LoginPage.class).checkInvalidEmailErrorMessage();
    }

    @When("registration page opened")
    public void registration_page_opened() {
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickSignupBtn();
    }

    @When("add invalid email or password format and click register")
    public void add_invalid_email_or_password_format_and_click_register() throws InterruptedException {
        PageGenerator.getInstance(LoginPage.class).addInvalidEmailFormat();
        PageGenerator.getInstance(LoginPage.class).registerWithInvalidPassFormat();
    }

    @Then("error message displayed")
    public void error_message_displayed() {
        PageGenerator.getInstance(LoginPage.class).checkInvalidPassErrorMessage();
    }
}
