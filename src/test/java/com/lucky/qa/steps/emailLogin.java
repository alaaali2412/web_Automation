package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.ProfilePage;
import com.lucky.qa.utilities.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class emailLogin {

    WebDriver driver = Hook.getDriver();
    public String loggedEmail;


    @Given("browser open,navigate to portal")
    public void browser_open_navigate_to_portal() {
    }

    @When("close the pop up click on login button")
    public void close_the_pop_up_click_on_login_button() {
        //PageGenerator.getInstance(HomePage.class, driver).closePopUp();
        PageGenerator.getInstance(HomePage.class, driver).clickSignInBtn();
    }

    @When("login with valid {string} and {string}")
    public void login_with_valid_and(String email, String password) {
        loggedEmail = PageGenerator.getInstance(LoginPage.class, driver).login(email, password);

    }

    @Then("verify the login")
    public void verify_the_login() {
        //PageGenerator.getInstance(HomePage.class,driver).closePopUp();
        PageGenerator.getInstance(HomePage.class, driver).openProfilePage();
        Assert.assertEquals(loggedEmail, PageGenerator.getInstance(ProfilePage.class, driver).getLoggedInEmail());


    }


}

