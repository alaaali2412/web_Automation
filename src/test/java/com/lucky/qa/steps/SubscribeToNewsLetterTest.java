package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SubscribeToNewsLetterTest {

    @Given("home page opened in {string}")
    public void homePageOpenedIn(String language) {
        PageGenerator.getInstance(HomePage.class).resetNewsLetterSubscription("Email", language);
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
    }

    @Given("user add registered email to subscribe news letter")
    public void user_add_registered_email_to_subscribe_news_letter() {
        PageGenerator.getInstance(HomePage.class).addRegisteredNewsLetterEmail("Email");
        PageGenerator.getInstance(HomePage.class).clickSubscribeBtn();
    }

    @And("success message will be displayed according to {string}")
    public void successMessageWillBeDisplayedAccordingTo(String language) {
        PageGenerator.getInstance(HomePage.class).checkNewsLetterSuccessMessage(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "NewsLetterSuccessMessage"));
    }

    @Given("user add anonymous email to subscribe news letter")
    public void user_add_anonymous_email_to_subscribe_news_letter() {
        PageGenerator.getInstance(HomePage.class).addNewEmail();
        PageGenerator.getInstance(HomePage.class).clickSubscribeBtn();
    }

    @Then("user get successful message and verification email")
    public void user_get_successful_message_and_verification_email() {
        //TODO this step after fixing this bug https://lucky-app.atlassian.net/browse/LCK-3559
    }


}
