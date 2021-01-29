package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import com.lucky.qa.pages.ProfilePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class googleLogin {
    WebDriver driver = Hook.getDriver();
    String loggedEmail;
    @Given("browser open,navigate to the portal")
    public void browser_open_navigate_to_the_portal()  {

    }

    @When("close the pop up click login with google")
    public void close_the_pop_up_click_login_with_google() throws InterruptedException{
        PageGenerator.getInstance(HomePage.class,driver).googleLogin();
    }

    @When("in the opened popup add {string} and {string}, click login")
    public void in_the_opened_popup_add_and_click_login(String gmail, String pass) throws InterruptedException {
      loggedEmail =   PageGenerator.getInstance(LoginPage.class,driver).loginGoogle(gmail,pass);
        driver.navigate().refresh();


    }

    @Then("verify that user login can login via google")
    public void verify_that_user_login_can_login_via_google() {
        PageGenerator.getInstance(HomePage.class, driver).openProfilePage();
        Assert.assertTrue((loggedEmail.equalsIgnoreCase(PageGenerator.getInstance(ProfilePage.class, driver).getLoggedInEmail())));
    }

}
