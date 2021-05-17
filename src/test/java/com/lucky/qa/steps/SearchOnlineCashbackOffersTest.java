package com.lucky.qa.steps;

import com.lucky.qa.APIs.ImplementAPIsMethods;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.OnlineCashbackPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchOnlineCashbackOffersTest {
    ImplementAPIsMethods implementAPIsMethods;
    int APIresult;

    @Given("browser, portal opened in {string}")
    public void browserPortalOpenedIn(String language) {
        PageGenerator.getInstance(HomePage.class).openPortalURL(language);

    }

    @When("add {string} in search field and click enter")
    public void add_in_search_field_and_click_enter(String keyword) {
        PageGenerator.getInstance(OnlineCashbackPage.class).searchOnlineCashabackOffers(keyword);
        implementAPIsMethods = new ImplementAPIsMethods();

    }

    @Then("verify result with {string} with the backend according to {string}")
    public void verifyResultWithWithTheBackendAccordingTo(String keyword, String languageValue) throws InterruptedException {
        APIresult = implementAPIsMethods.getCountOfAffiliateMerchants(keyword, languageValue);
        Assert.assertEquals(APIresult, PageGenerator.getInstance(OnlineCashbackPage.class).getMerchantFilteredList());
    }
}
