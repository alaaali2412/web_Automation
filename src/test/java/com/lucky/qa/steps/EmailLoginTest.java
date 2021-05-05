package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.ProfilePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class EmailLoginTest {
    public String loggedEmail;

    @Given("browser open,navigate to portal")
    public void browser_open_navigate_to_portal() {
    }

    @When("close the pop up click on login button")
    public void close_the_pop_up_click_on_login_button() {
        //PageGenerator.getInstance(HomePage.class).closePopUp();
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
    }

    @When("^login with valid email and pass$")
    public void login_with_valid_email_and_pass() {
        loggedEmail = PageGenerator.getInstance(LoginPage.class).login("LoginData.properties"
                , "Email", "Password");
    }

    @Then("verify the login")
    public void verify_the_login() {
        //PageGenerator.getInstance(HomePage.class,driver).closePopUp();
        PageGenerator.getInstance(HomePage.class).openProfilePage();
        Assert.assertEquals(loggedEmail, PageGenerator.getInstance(ProfilePage.class).getLoggedInEmail());


    }
}

