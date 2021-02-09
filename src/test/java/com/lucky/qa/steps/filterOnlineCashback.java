package com.lucky.qa.steps;


import com.lucky.qa.APIs.ImplementAPIsMethods;
import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.OnlineCashbackPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;



public class filterOnlineCashback {
    WebDriver driver = Hook.getDriver();
    public String categorySelected;
    ImplementAPIsMethods implementMethodsObject;
    int APIresult;

    @Given("browser opened,portal opened")
    public void browser_opened_portal_opened() {

    }

    @When("user logged in")
    public void user_logged_in() {
        PageGenerator.getInstance(HomePage.class, driver).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class, driver).login();
    }


    @When("user click OnlineCashback and select {string}")
    public void user_click_online_cashback_and_select(String category) {
        implementMethodsObject = new ImplementAPIsMethods();
        categorySelected = PageGenerator.getInstance(HomePage.class, driver).clickOnlineCashback(category);
        PageGenerator.getInstance(OnlineCashbackPage.class, driver).checkOnlineCashbackPageOpens(categorySelected);
        APIresult = implementMethodsObject.getAffiliateMerchantsByCategory(category);


    }

    @Then("verify that user can filter")
    public void verify_that_user_can_filter() throws InterruptedException {
        int numberOfMerchant = PageGenerator.getInstance(OnlineCashbackPage.class, driver).getMerchantFilteredList();
        Assert.assertEquals(numberOfMerchant, APIresult);

    }


}
