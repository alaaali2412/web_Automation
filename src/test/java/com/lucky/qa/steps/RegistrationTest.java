package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RegistrationTest {
/*
    @Given("portal open in {string} user click signup button")
    public void portalOpenInUserClickSignupButton(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
        PageGenerator.getInstance(HomePage.class).clickSignupBtn();
    }

    @When("user add all the mandatory fields of Registration")
    public void userAddAllTheMandatoryFieldsOfRegistration() {
        PageGenerator.getInstance(RegistrationPage.class).addRegistrationName();
        PageGenerator.getInstance(RegistrationPage.class).addNewEmail();
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

    @Then("user can not login without verifying the email {string}")
    public void user_can_not_login_without_verifying_the_email(String language) {
        PageGenerator.getInstance(RegistrationPage.class).loginWithUnregisteredEmail();
        PageGenerator.getInstance(RegistrationPage.class).checkUnverifiedMailErrorMessage
                (PageGenerator.getInstance(BasePage.class).detectLanguage(language, "UnverifiedEmailErrorMsg"));
    }

    @When("user open his email account, open the received email from lucky")
    public void userOpenHisEmailAccountOpenTheReceivedEmailFromLucky() {
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
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
        PageGenerator.getInstance(HomePage.class).openProfilePage();
        Assert.assertEquals(loggedEmail, PageGenerator.getInstance(ProfilePage.class).getLoggedInEmail());
        PageGenerator.getInstance(RegistrationPage.class).saveTheRegistrationDetails();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
    }*/
}
