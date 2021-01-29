package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class performCashoutRequestByAman {
    WebDriver driver = Hook.getDriver();

    @Given("browser, portal open")
    public void browser_portal_open() {

    }

    @When("user login {string} and {string}")
    public void user_login_and(String string, String string2) {

    }

    @When("in wallet page make sure cashback balance >= {int} EGP")
    public void in_wallet_page_make_sure_cashback_balance_egp(Integer int1) {

    }


    @When("click request Cashout and choose Aman")
    public void click_request_cashout_and_choose_aman() {

    }

    @When("add the amount and click continue")
    public void add_the_amount_and_click_continue() {

    }

    @When("user get that Cashout successful")
    public void user_get_that_cashout_successful() {

    }

    @Then("verify that cashout deducted from user balance and cashback")
    public void vrify_that_cashout_deducted_from_user_balance_and_cashback() {

    }

}
