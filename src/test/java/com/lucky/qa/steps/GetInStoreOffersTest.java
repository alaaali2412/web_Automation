package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetInStoreOffersTest {

    @Given("browser open,open portal in {string}, user login")
    public void browserOpenOpenPortalInUserLogin(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login("LoginData.properties"
                , "Email", "Password");
    }

    @When("click on instore offers")
    public void click_on_instore_offers() {
        PageGenerator.getInstance(HomePage.class).clickInStoreBtn();
    }

    @When("select an offer and click it")
    public void select_an_offer_and_click_it() {
        PageGenerator.getInstance(InStoreOfferPage.class).selectInStoreItem();
    }

    @When("click get offer")
    public void click_get_offer() {
        PageGenerator.getInstance(OfferDetailsPage.class).clickGetOffer();
    }

    @Then("popup displayed in {string} that user should download lucky app")
    public void popupDisplayedInThatUserShouldDownloadLuckyApp(String language) {
        PageGenerator.getInstance(OfferDetailsPage.class).closePopUP(
                PageGenerator.getInstance(BasePage.class).detectLanguage(language, "GetOfferPopUpHeader"));
        PageGenerator.getInstance(HomePage.class).openHomeScreen();
        PageGenerator.getInstance(HomePage.class).clickLogOut();
    }
}
