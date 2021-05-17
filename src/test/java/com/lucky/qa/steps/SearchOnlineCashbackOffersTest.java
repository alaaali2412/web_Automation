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

    @Given("browser, portal opened")
    public void browser_portal_opened() {
        PageGenerator.getInstance(HomePage.class).checkThatHomePageOpened();
    }

    @When("add {string} in search field and click enter")
    public void add_in_search_field_and_click_enter(String keyword) {
        PageGenerator.getInstance(OnlineCashbackPage.class).searchOnlineCashabackOffers(keyword);
        implementAPIsMethods = new ImplementAPIsMethods();
        APIresult = implementAPIsMethods.getCountOfAffiliateMerchants(keyword);
    }

    @Then("verify the search result with the backend")
    public void verify_the_search_result_with_the_backend() throws InterruptedException {
        Assert.assertEquals(APIresult, PageGenerator.getInstance(OnlineCashbackPage.class).getMerchantFilteredList());
    }

}
