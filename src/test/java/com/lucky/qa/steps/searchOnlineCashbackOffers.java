package com.lucky.qa.steps;

import com.lucky.qa.APIs.ImplementAPIsMethods;
import com.lucky.qa.connectors.Hook;
import com.lucky.qa.pages.OnlineCashbackPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class searchOnlineCashbackOffers {
    WebDriver driver = Hook.getDriver();
    ImplementAPIsMethods implementAPIsMethods;
    int APIresult;

    @Given("browser, portal opened")
    public void browser_portal_opened() {

    }

    @When("add {string} in search field and click enter")
    public void add_in_search_field_and_click_enter(String keyword) throws InterruptedException {
        PageGenerator.getInstance(OnlineCashbackPage.class, driver).searchOnlineCashabckOffers(keyword);
        implementAPIsMethods = new ImplementAPIsMethods();
        APIresult = implementAPIsMethods.getCountOfAffiliateMerchants(keyword);

    }

    @Then("verify the search result with the backend")
    public void verify_the_search_result_with_the_backend() throws InterruptedException {
        Assert.assertEquals(APIresult, PageGenerator.getInstance(OnlineCashbackPage.class, driver).getMerchantFilteredList());
    }

}
