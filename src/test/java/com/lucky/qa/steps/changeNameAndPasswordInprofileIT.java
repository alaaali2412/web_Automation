package com.lucky.qa.steps;


import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.ProfilePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class changeNameAndPasswordInprofileIT {

    String loggedInEmail;
    @Given("portal and browser open, user login")
    public void portal_and_browser_open_user_login() {
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login();
    }


    @When("open profile screen")
    public void open_profile_screen() {
        PageGenerator.getInstance(HomePage.class).openProfilePage();
    }

    @When("change the name and click save")
    public void change_the_name_and_click_save() {
        PageGenerator.getInstance(ProfilePage.class).changeName();
    }

    @When("change the password click save")
    public void change_the_password_click_save() {
        PageGenerator.getInstance(ProfilePage.class).changePassword();
    }

    @When("user log out and login again")
    public void user_log_out_and_login_again() {
        PageGenerator.getInstance(ProfilePage.class).clickLogout();
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        loggedInEmail = PageGenerator.getInstance(LoginPage.class).login();
    }

    @Then("verify that data changed")
    public void verify_that_data_changed() {
        PageGenerator.getInstance(HomePage.class).openProfilePage();
        Assert.assertEquals(loggedInEmail, PageGenerator.getInstance(ProfilePage.class).getLoggedInEmail());

    }

}
