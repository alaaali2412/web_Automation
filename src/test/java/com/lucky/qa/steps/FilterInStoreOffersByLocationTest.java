package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.InStoreOfferPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FilterInStoreOffersByLocationTest {

    @Given("browser open,portal,open In_store offer")
    public void browser_open_portal_open_in_store_offer() {
        PageGenerator.getInstance(HomePage.class).clkInStoreBtn();
    }

    @When("user choose specfic {string} from the right menu")
    public void user_choose_specfic_from_the_right_menu(String Location) {
        PageGenerator.getInstance(InStoreOfferPage.class).filterByLocation(Location);
    }

    @When("select {string} if required")
    public void select_if_required(String subLocation) {
        PageGenerator.getInstance(InStoreOfferPage.class).selectSubLocation(subLocation);
        PageGenerator.getInstance(InStoreOfferPage.class).clickApplyBtn();
    }

    @Then("in-Store offers filtered according to the selected location")
    public void in_store_offers_filtered_according_to_the_selected_location() {
        PageGenerator.getInstance(InStoreOfferPage.class).clickClearBtn();
        // TODO verify result with backend
    }

    @Given("browser open,portal in {string},open In_store offer")
    public void browserOpenPortalInOpenIn_storeOffer(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).clkInStoreBtn();
    }
}
