package com.lucky.qa.steps;


import com.lucky.qa.APIs.ImplementAPIsMethods;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.OnlineCashbackPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class FilterOnlineCashbackTest {

    public String categorySelected;
    ImplementAPIsMethods implementMethodsObject;
    int APIresult;

    @Given("browser opened,portal opened in {string}")
    public void browserOpenedPortalOpenedIn(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);
    }

    @When("user click OnlineCashback and select {string}")
    public void user_click_online_cashback_and_select(String category) {
        implementMethodsObject = new ImplementAPIsMethods();
        categorySelected = PageGenerator.getInstance(HomePage.class).clickOnlineCashback(category);
        PageGenerator.getInstance(OnlineCashbackPage.class).checkOnlineCashbackPageOpens(categorySelected);

    }

    @Then("verify filter with {string} with backend according to {string}")
    public void verifyFilterWithWithBackendAccordingTo(String category, String language) throws InterruptedException {
        APIresult = implementMethodsObject.getAffiliateMerchantsByCategory(category, implementMethodsObject.languageValue(language));
        int numberOfMerchant = PageGenerator.getInstance(OnlineCashbackPage.class).getMerchantFilteredList();
        Assert.assertEquals(numberOfMerchant, APIresult);
        PageGenerator.getInstance(OnlineCashbackPage.class).clickClearBtn();

    }
}
