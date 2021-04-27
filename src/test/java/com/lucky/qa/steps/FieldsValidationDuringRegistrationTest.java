package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FieldsValidationDuringRegistrationTest {
    @Given("Registration page opened")
    public void registration_page_opened() {
        PageGenerator.getInstance(HomePage.class).clickSignupBtn();
    }

    @When("add invalid values in registration fields")
    public void add_invalid_values_in_registration_fields() {
        PageGenerator.getInstance(RegistrationPage.class).addInvalidName();
        PageGenerator.getInstance(RegistrationPage.class).registerWithInvalidEmailFormat();
        PageGenerator.getInstance(RegistrationPage.class).registerWithPassLessThanSixCharacters
                (PageGenerator.getInstance(BasePage.class).detectLanguage("PasswordLessThanSixErrorMsg"));
        PageGenerator.getInstance(RegistrationPage.class).registerWithInvalidPassFormat();
        PageGenerator.getInstance(RegistrationPage.class).registerWithInvalidRepeatPass();
    }

    @Then("error messages displayed at the fields that contain invalid values")
    public void error_messages_displayed_at_the_fields_that_contain_invalid_values() {
        PageGenerator.getInstance(RegistrationPage.class).checkInvalidNameErrorMessage
                (PageGenerator.getInstance(BasePage.class).detectLanguage("NameRegistrationErrorMsg"));
        PageGenerator.getInstance(RegistrationPage.class).checkInvalidEmailErrorMessage
                (PageGenerator.getInstance(BasePage.class).detectLanguage("InValidEmailErrorMsg"));
        PageGenerator.getInstance(RegistrationPage.class).checkInvalidPassErrorMessage
                (PageGenerator.getInstance(BasePage.class).detectLanguage("ComplexPassErrorMessage"));
        PageGenerator.getInstance(RegistrationPage.class).checkRepeatPassErrorMessage
                (PageGenerator.getInstance(BasePage.class).detectLanguage("RepeatPassErrorMessage"));
    }
}
