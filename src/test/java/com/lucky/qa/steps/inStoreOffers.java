package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class inStoreOffers {

    WebDriver driver = Hook.getDriver();


    @Given("browser open,open portal, user login {string} , {string}")
    public void browser_open_open_portal_user_login(String email, String password) throws InterruptedException {
        PageGenerator.getInstance(HomePage.class,driver).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class, driver).login(email,password);
    }


    @When("click on instore offers")
    public void click_on_instore_offers() {
        PageGenerator.getInstance(HomePage.class,driver).clkInStoreBtn();

    }

    @When("select an offer and click it")
    public void select_an_offer_and_click_it() throws InterruptedException {
        PageGenerator.getInstance(InStoreOfferPage.class,driver).selectInStoreItem();


    }

    @When("click get offer")
    public void click_get_offer() {
        PageGenerator.getInstance(OfferDetialsPage.class,driver).clickGetOffer();

    }

    @Then("popup displayed that userf should download lucky app")
    public void popup_displayed_that_userf_should_download_lucky_app() {
        PageGenerator.getInstance(OfferDetialsPage.class,driver).clsoePopUP();

    }



}
