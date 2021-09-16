package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.RegistrationPage;
import com.lucky.qa.test;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FieldsValidationDuringRegistrationTest {
test t = new test();
    @Given("portal {string} Registration page opened")
    public void portalRegistrationPageOpened(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
        PageGenerator.getInstance(HomePage.class).clickSignupBtn();
    }

    @When("add invalid values {string}in registration fields")
    public void addInvalidValuesInRegistrationFields(String Language) {
        PageGenerator.getInstance(RegistrationPage.class).addInvalidName();
        PageGenerator.getInstance(RegistrationPage.class).registerWithInvalidEmailFormat();
        PageGenerator.getInstance(RegistrationPage.class).registerWithPassLessThanSixCharacters
                (t.detectLanguage(Language, "PasswordLessThanSixErrorMsg"));
        PageGenerator.getInstance(RegistrationPage.class).registerWithInvalidPassFormat();
        PageGenerator.getInstance(RegistrationPage.class).registerWithInvalidRepeatPass();
    }

    @Then("error messages displayed at the fields that contain invalid values according to {string}")
    public void errorMessagesDisplayedAtTheFieldsThatContainInvalidValuesAccordingTo(String Language) {
        PageGenerator.getInstance(RegistrationPage.class).checkInvalidNameErrorMessage
                (t.detectLanguage(Language, "NameRegistrationErrorMsg"));
        PageGenerator.getInstance(RegistrationPage.class).checkInvalidEmailErrorMessage
                (t.detectLanguage(Language, "InValidEmailErrorMsg"));
        PageGenerator.getInstance(RegistrationPage.class).checkInvalidPassErrorMessage
                (t.detectLanguage(Language, "ComplexPassErrorMessage"));
        PageGenerator.getInstance(RegistrationPage.class).checkRepeatPassErrorMessage
                (t.detectLanguage(Language, "RepeatPassErrorMessage"));
    }
}
