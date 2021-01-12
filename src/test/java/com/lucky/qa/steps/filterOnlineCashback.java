package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class filterOnlineCashback {
    WebDriver driver = Hook.getDriver();

    @Given("browser open,navigate to portal, login {string} and {string}")
    public void browser_open_navigate_to_portal_login_and(String string, String string2) {

    }

    @When("click online cashback offers and select {string}")
    public void click_online_cashback_offers_and_select(String string) {

    }

    @Then("online cashback page opens and same category selected")
    public void online_cashback_page_opens_and_same_category_selected() {

    }
}
