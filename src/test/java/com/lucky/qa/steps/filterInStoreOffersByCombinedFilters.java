package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.InStoreOfferPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class filterInStoreOffersByCombinedFilters {
    WebDriver driver = Hook.getDriver();
    @Given("browser & portal opened,  open In_store offer screen")
    public void browser_portal_opened_open_in_store_offer_screen() {
        PageGenerator.getInstance(HomePage.class,driver).clkInStoreBtn();

    }

    @When("user filter by location location {string}, {string}")
    public void user_filter_by_location_location(String Location, String subLocation)  {
        PageGenerator.getInstance(InStoreOfferPage.class,driver).filterByLocation(Location);
        PageGenerator.getInstance(InStoreOfferPage.class,driver).selectSubLocation(subLocation);

    }
    @When("user choose specific {string},  {string}, {string} if required")
    public void user_choose_specific_if_required(String mainCategory, String mainSubCategory, String subCategory) {
        PageGenerator.getInstance(InStoreOfferPage.class, driver).designPattern(InStoreOfferPage.CategoryType.SubCategory,
                mainCategory, mainSubCategory, subCategory);


    }



    @When("combine between both filters then click apply")
    public void combine_between_both_filters_then_click_apply() {
        PageGenerator.getInstance(InStoreOfferPage.class,driver).clickApplyBtn();
    }

}
