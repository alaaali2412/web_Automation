package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.ProfilePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginByEmailTest {
    public String loggedEmail;

    @When("close the pop up then click login")
    public void close_the_pop_up_then_click_login() {
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
    }

    @When("add email and pass, click login")
    public void add_email_and_pass_click_login() {
        loggedEmail = PageGenerator.getInstance(LoginPage.class).login("LoginData.properties"
                , "Email", "Password");
        PageGenerator.getInstance(HomePage.class).checkIfPopUpExist();
    }


    @Then("verify that user login")
    public void verify_that_user_login() {
        PageGenerator.getInstance(HomePage.class).openProfilePage();
        Assert.assertEquals(loggedEmail, PageGenerator.getInstance(ProfilePage.class).getLoggedInEmail());
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
    }
}
