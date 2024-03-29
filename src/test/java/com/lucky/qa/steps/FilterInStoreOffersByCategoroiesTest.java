package com.lucky.qa.steps;

import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.InStoreOfferPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FilterInStoreOffersByCategoroiesTest {

    @Given("browser open,portal,open {string} In_store offer screen")
    public void browserOpenPortalOpenIn_storeOfferScreen(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
        PageGenerator.getInstance(HomePage.class).clickInStoreBtn();
    }

    @When("user choose specfic {string} from right menu, choose {string}, {string} if required")
    public void user_choose_specfic_from_right_menu_choose_if_required(String mainCategory, String mainSubCategory, String subCategory) {
        PageGenerator.getInstance(InStoreOfferPage.class).designPattern(InStoreOfferPage.CategoryType.SubCategory,
                mainCategory, mainSubCategory, subCategory);
    }

    @Then("in-Store offers filtered according to the selected category")
    public void in_store_offers_filtered_according_to_the_selected_category() {
        PageGenerator.getInstance(InStoreOfferPage.class).clickApplyBtn();
    }
}
