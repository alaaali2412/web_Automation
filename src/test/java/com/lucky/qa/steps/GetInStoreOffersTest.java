package com.lucky.qa.steps;

import com.lucky.qa.pages.*;
import com.lucky.qa.utilities.LanguageReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetInStoreOffersTest {
    LanguageReader lang = new LanguageReader();

    @When("browser open,open portal, user login")
    public void browser_openopen_portal_user_login() {
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class).login("LoginData.properties"
                , "Email", "password");
    }

    @When("click on instore offers")
    public void click_on_instore_offers() {
        PageGenerator.getInstance(HomePage.class).clkInStoreBtn();
    }

    @When("select an offer and click it")
    public void select_an_offer_and_click_it() {
        PageGenerator.getInstance(InStoreOfferPage.class).selectInStoreItem();
    }

    @When("click get offer")
    public void click_get_offer() {
        PageGenerator.getInstance(OfferDetailsPage.class).clickGetOffer();
    }

    @Then("popup displayed that userf should download lucky app")
    public void popup_displayed_that_userf_should_download_lucky_app() {
        PageGenerator.getInstance(OfferDetailsPage.class).closePopUP(lang.detectLanguage("GetOfferPopUpHeader"));
    }
}
