package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
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

    @FindBy(xpath = "//footer/div[1]/span/img")
    private WebElement moveToTheTopBtn;

    @FindBy(xpath = "//*[@class='close-modal']")
    private WebElement popupCloseBtn;

    @FindBy(xpath = "//*[@class = 'row filteration-buttons'][1]/button[1]")
    private WebElement clearBtn;

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
        waitForPageToLoad();
        return new ArrayList<>(merchants).size();
    }

    public void searchOnlineCashabackOffers(String StoreName) throws InterruptedException {
        Thread.sleep(1000);
        clearField(searchField);
        addText(searchField, StoreName);
        searchField.sendKeys(Keys.ENTER);
        waitVisibilityOfElement(onlineCashbackTitle);
    }

    public void clickClearBtn() {
        scrollToTopOfScreen();
        clickButton(clearBtn);
    }
}
