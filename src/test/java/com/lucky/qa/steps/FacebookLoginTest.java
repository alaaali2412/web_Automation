package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FacebookLoginTest {

    @Given("browser open,navigate to portal URl {string}")
    public void browser_open_navigate_to_portal_u_rl(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).checkThatHomePageOpened();
    }

    @When("close the pop up click login with facebook")
    public void close_the_pop_up_click_login_with_facebook() {
        PageGenerator.getInstance(HomePage.class).facebookLogin();
    }

    @When("in the opened popup add facebook {string} and {string}, click login")
    public void in_the_opened_popup_add_facebook_and_click_login(String email, String pass) {
        PageGenerator.getInstance(LoginPage.class).loginFacebook(email, pass);
    }

    @Then("verify that user login can login via facebook")
    public void verify_that_user_login_can_login_via_facebook() {
        // TODO ( Alaa) I can not login with Facebook in KSA
    }
}
