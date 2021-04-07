package com.lucky.qa.steps;

import com.lucky.qa.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RegistrationTest {
    @Given("user click signup button")
    public void userClickSignupButton() {
        PageGenerator.getInstance(HomePage.class).clickSignupBtn();
    }

    @When("user add all the mandatory fields of Registration")
    public void userAddAllTheMandatoryFieldsOfRegistration() throws InterruptedException {
        PageGenerator.getInstance(RegistrationPage.class).addRegistrationName();
        PageGenerator.getInstance(RegistrationPage.class).addRegistrationEmail();
        PageGenerator.getInstance(RegistrationPage.class).addRegistrationPasswords();
    }

    @When("click register button")
    public void clickRegisterButton() {
        PageGenerator.getInstance(RegistrationPage.class).clickRegisterBtn();
    }

    @When("try to login directly with same email and password")
    public void tryToLoginDirectlyWithSameEmailAndPassword() {
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
    }

    @Then("user can not login without verifying the email")
    public void userCanNotLoginWithoutVerifyingTheEmail() throws InterruptedException {
        PageGenerator.getInstance(LoginPage.class).login(
                "RegistrationData.properties", "RegistrationEmail", "RegistrationPassword");
        PageGenerator.getInstance(RegistrationPage.class).checkUnverifiedMailErrorMessage();
    }

    @When("user open his email account, open the received email from lucky")
    public void userOpenHisEmailAccountOpenTheReceivedEmailFromLucky() throws InterruptedException {
        PageGenerator.getInstance(RegistrationPage.class).openVerificationMail();
    }

    @When("verify the registered email")
    public void verifyTheRegisteredEmail() {
        PageGenerator.getInstance(RegistrationPage.class).verifyRegistrationEmail();
    }

    @Then("user can login with the new email")
    public void userCanLoginWithTheNewEmail() {
        String loggedEmail = PageGenerator.getInstance(LoginPage.class).login(
                "RegistrationData.properties", "RegistrationEmail", "RegistrationPassword");
        PageGenerator.getInstance(HomePage.class).openProfilePage();
        Assert.assertEquals(loggedEmail, PageGenerator.getInstance(ProfilePage.class).getLoggedInEmail());
    }
}
