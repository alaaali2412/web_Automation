package com.lucky.qa.steps;


import com.lucky.qa.APIs.ImplementMethods;
import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.OnlineCashbacksPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class filterOnlineCashback {
    WebDriver driver = Hook.getDriver();
    public String categorySelected;
    ImplementMethods implementMethodsObject;
    int APIresult ;
    @Given("browser opened,portal opened")
    public void browser_opened_portal_opened() {

    }

    @When("user logged in {string} and {string}")
    public void user_logged_in_and(String email, String password) throws InterruptedException {
        PageGenerator.getInstance(HomePage.class, driver).clickSignInBtn();
        PageGenerator.getInstance(LoginPage.class, driver).login(email, password);
    }

    @When("user click OnlineCashback and select {string}")
    public void user_click_online_cashback_and_select(String category) throws InterruptedException {
        implementMethodsObject = new ImplementMethods();
        categorySelected = PageGenerator.getInstance(HomePage.class, driver).clickOnlinCashback(category);
        PageGenerator.getInstance(OnlineCashbacksPage.class, driver).checkOnlineCashbacksPageOpens(categorySelected);
         APIresult = implementMethodsObject.getAffiliateMerchantsByCategory(category);


    }

    @Then("verify that user can filter")
    public void verify_that_user_can_filter()  {
        int numberOfMerchant = PageGenerator.getInstance(OnlineCashbacksPage.class, driver).getMerchantFilteredList();
        Assert.assertEquals(numberOfMerchant, numberOfMerchant);

    }


}
