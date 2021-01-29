package com.lucky.qa.steps;

import com.lucky.qa.connectors.Hook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class performCashoutRequestByBankAccount {
    WebDriver driver = Hook.getDriver();
    @Given("browser, portal opened,user login {string} and {string}")
    public void browser_portal_opened_user_login_and(String string, String string2) {

    }

    @When("wallet opens and cashback balance > {int} EGP")
    public void wallet_opens_and_cashback_balance_egp(Integer int1) {
    }

    @When("click request Cashout and choose Bank Account")
    public void click_request_cashout_and_choose_bank_account() {

    }

    @When("add all the mandatory fields and click continue")
    public void add_all_the_mandatory_fields_and_click_continue() {

    }

    @When("user get that the Cashout done successfully")
    public void user_get_that_the_cashout_done_successfully() {

    }

    @Then("verify that cashout amount deducted from user balance and cashback")
    public void vrify_that_cashout_amount_deducted_from_user_balance_and_cashback() {

    }

}
