package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.ContactUsPage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendIssueToSupportTeamTest {

    @Given("home page open in {string}")
    public void homePageOpenIn(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).checkThatHomePageOpened();
    }

    @When("user click on contact us")
    public void user_click_on_contact_us() {
        PageGenerator.getInstance(HomePage.class).clickContactUsBtn();

    }

    @Then("user can text message according to {string}that explain to user how to contact support team")
    public void userCanTextMessageAccordingToThatExplainToUserHowToContactSupportTeam(String language) {
        PageGenerator.getInstance(ContactUsPage.class).checkContactUsTexts(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "ContactUsTitle"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "ContactUsTextMessage"),
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "ContactEmail"));
    }
}
