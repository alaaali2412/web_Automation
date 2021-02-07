package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.InStoreOfferPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class filterInStoreOffersByCategoroies {
    WebDriver driver = Hook.getDriver();

    @Given("browser open,portal,open In_store offer screen")
    public void browser_open_portal_open_in_store_offer_screen() {
        PageGenerator.getInstance(HomePage.class, driver).clkInStoreBtn();
    }

    @When("user choose specfic {string} from right menu, choose {string}, {string} if required")
    public void user_choose_specfic_from_right_menu_choose_if_required(String mainCategory, String mainSubCategory, String subCategory)  {
        PageGenerator.getInstance(InStoreOfferPage.class, driver).designPattern(InStoreOfferPage.CategoryType.SubCategory,
                mainCategory, mainSubCategory, subCategory);
        PageGenerator.getInstance(InStoreOfferPage.class, driver).clickApplyBtn();
    }

    @Then("in-Store offers filtered according to the selected category")
    public void in_store_offers_filtered_according_to_the_selected_category() {

    }

}
