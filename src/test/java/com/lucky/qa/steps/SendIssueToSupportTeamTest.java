package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.ContactUsPage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendIssueToSupportTeamTest {
    @Given("home page open")
    public void home_page_open() {
        PageGenerator.getInstance(HomePage.class).checkThatHomePageOpened();
    }

    @When("user click on contact us")
    public void user_click_on_contact_us() {
        PageGenerator.getInstance(HomePage.class).clickContactUsBtn();

    }

    @Then("user can text message that explain to user how to contact support team")
    public void user_can_text_message_that_explain_to_user_how_to_contact_support_team() {
        PageGenerator.getInstance(ContactUsPage.class).checkContactUsTexts(
                PageGenerator.getInstance(BasePage.class).detectLanguage("ContactUsTitle"),
                PageGenerator.getInstance(BasePage.class).detectLanguage("ContactUsTextMessage"),
                PageGenerator.getInstance(BasePage.class).detectLanguage("ContactEmail"));
    }


}
