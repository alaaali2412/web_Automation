package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;


public class StepsDefinition {


    protected WebDriver driver = Hook.getDriver();

    @Given("open browser")
    public void openBrowser() throws Exception {
        PageGenerator.getInstance(HomePage.class, driver).goToThePage();
    }

    @When("I enter the home, I close the registration pop up")
    public void iEnterTheHomeICloseTheRegistrationPopUp() throws Exception {
        PageGenerator.getInstance(LoginPage.class, driver).closePopUpRegister();
    }

    @Then("go to change language and select English")
    public void goToChangeLanguageAndSelectEnglish() throws Exception {
        PageGenerator.getInstance(HomePage.class,driver).dropDownLanguage();
        PageGenerator.getInstance(HomePage.class,driver).selectItemLanguage();
    }

    @When("login with facebook")
    public void loginWithFacebook() throws Exception {
        PageGenerator.getInstance(LoginPage.class,driver).clickOnFacebookLogin();
        PageGenerator.getInstance(LoginPage.class, driver).setEmail();
    }

    @When("login with email registered")
    public void loginWithEmailRegistered() throws Exception {
        PageGenerator.getInstance(LoginPage.class,driver).login();

    }

    @Then("verify the login")
    public void verifyTheLogin() throws Exception {
        //PageGenerator.getInstance(HomePage.class,driver).
    }
}
