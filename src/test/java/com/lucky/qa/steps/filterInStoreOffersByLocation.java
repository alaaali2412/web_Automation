package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.InStoreOfferPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class filterInStoreOffersByLocation {
    WebDriver driver = Hook.getDriver();

    @Given("browser open,portal,open In_store offer")
    public void browser_open_portal_open_in_store_offer() {
        PageGenerator.getInstance(HomePage.class,driver).clkInStoreBtn();

    }

    @When("user choose specfic {string} from the right menu")
    public void user_choose_specfic_from_the_right_menu(String Location)  {
        PageGenerator.getInstance(InStoreOfferPage.class,driver).filterByLocation(Location);

    }

    @When("select {string} if required")
    public void select_if_required(String subLocation)  {
        PageGenerator.getInstance(InStoreOfferPage.class,driver).selectSubLocation(subLocation);
    }

    @Then("in-Store offers filtered according to the selected location")
    public void in_store_offers_filtered_according_to_the_selected_location() {
        //verify this method with backend ,not ready yet

    }

}
