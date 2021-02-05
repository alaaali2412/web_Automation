package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class facebookLogin {

    WebDriver driver = Hook.getDriver();


    @Given("browser open,navigate to portal URl")
    public void browser_open_navigate_to_portal_u_rl() {

    }

    @When("close the pop up click login with facebook")
    public void close_the_pop_up_click_login_with_facebook()  {
       PageGenerator.getInstance(HomePage.class,driver).facebookLogin();

    }

    @When("in the opened popup add facebook {string} and {string}, click login")
    public void in_the_opened_popup_add_facebook_and_click_login(String email, String pass)  {
        PageGenerator.getInstance(LoginPage.class,driver).loginFacebook(email,pass);

    }



    @Then("verify that user login can login via facebook")
    public void verify_that_user_login_can_login_via_facebook() {

    }


}
