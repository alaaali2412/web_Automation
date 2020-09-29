package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.InStoreOfferPage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsStoreOffers {

    public WebDriver driver = Hook.getDriver();

    @Given("go to in store offers")
    public void openStoreOffer() throws Exception {
        PageGenerator.getInstance(HomePage.class, driver).clickInStoreOffers();
    }

    @And("select one offer")
    public void selectOneOffer() throws Exception {
        PageGenerator.getInstance(InStoreOfferPage.class, driver).selectCard();
    }

    @And("click in get offer")
    public void clickInGetOffer() throws Exception {
        PageGenerator.getInstance(InStoreOfferPage.class, driver).getOffer();
    }


    @Then("verify modal to download app appear")
    public void verifyModalToDownloadAppAppear() throws Exception {
        PageGenerator.getInstance(InStoreOfferPage.class, driver).checkModalOffer();
    }
}
