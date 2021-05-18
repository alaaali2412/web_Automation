package com.lucky.qa.steps;

import com.lucky.qa.pages.ContactUsPage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendIssueToSupportTeamTest {
    @Given("user open contact us screen")
    public void user_open_contact_us_screen() {
        PageGenerator.getInstance(HomePage.class).clickContactUsBtn();
    }

    @When("Leave all the fields empty")
    public void leaveAllTheFieldsEmpty() {
    }

    @When("Click send")
    public void clickSend() {
        PageGenerator.getInstance(ContactUsPage.class).clickSendBtn();
    }

    @Then("Validation messages displayed at the mandatory fields")
    public void validationMessagesDisplayedAtTheMandatoryFields() {
        /*  PageGenerator.getInstance(ContactUsPage.class).validationMessages(
              PageGenerator.getInstance(BasePage.class).detectLanguage("ContactUsNameFieldErrorMsg"),
                PageGenerator.getInstance(BasePage.class).detectLanguage("ContactUsMobileNumberFieldErrorMsg"),
                PageGenerator.getInstance(BasePage.class).detectLanguage("ContactUsSubjectListErrorMsg"),
                PageGenerator.getInstance(BasePage.class).detectLanguage("ContactUsTellUsMoreFieldErrorMsg"));*/
    }

    @When("fill all the mandatory fields {string} , {string}, {string}")
    public void fillAllTheMandatoryFields(String Name, String MobileNumber, String Text) {
        PageGenerator.getInstance(ContactUsPage.class).addMandatoryFields(Name, MobileNumber, Text);
        PageGenerator.getInstance(ContactUsPage.class).selectSubject(3);
    }

    @When("click send button")
    public void click_send_button() {
        PageGenerator.getInstance(ContactUsPage.class).clickSendBtn();
    }

    @Then("user get successful message")
    public void user_get_successful_message() {
/*
        PageGenerator.getInstance(ContactUsPage.class).checkSuccessMessage(
                PageGenerator.getInstance(BasePage.class).detectLanguage("ContactUsSuccessMsg"));
*/
    }
    //TODo (Alaa) maintain the scenario to match the new design
}
