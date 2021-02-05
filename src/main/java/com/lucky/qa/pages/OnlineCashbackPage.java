package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class OnlineCashbackPage extends BasePage {
    @FindBy(xpath = "//*[@id='responsive-navbar-nav']//input")
    private WebElement searchField;

    @FindBy(xpath = "//div/div[2]/div/div[1]/div/h3")
    private WebElement onlineCashbackTitle;

    @FindBy(xpath = "//div[1]/form/div/div")
    private List<WebElement> filterList;

    @FindBy(xpath = "//div/div[2]/div/div[2]/div[1]/div")
    private List<WebElement> merchants;

    public OnlineCashbackPage(WebDriver driver) {
        super(driver);
    }

    public void checkOnlineCashbackPageOpens(String selected) {
        waitVisibilityOfElement(onlineCashbackTitle);
        for (WebElement category : filterList) {
            WebElement selectedCheckbox = category.findElement(By.tagName("input"));
            if (selectedCheckbox.isSelected() == true) {
                Assert.assertEquals(getText(category), selected);

            }

            break;
        }

    }

    public int getMerchantFilteredList() throws InterruptedException {
        scrollToEndOfScreen();
        Thread.sleep(1000);
        return new ArrayList<>(merchants).size();
    }

    public void searchOnlineCashabckOffers(String StoreName) throws InterruptedException {
        clearField(searchField);
        addText(searchField, StoreName);
        searchField.sendKeys(Keys.ENTER);
        waitVisibilityOfElement(onlineCashbackTitle);
        Thread.sleep(10000);
    }
}
