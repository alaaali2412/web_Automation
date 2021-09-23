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
    @FindBy(css = "#responsive-navbar-nav input")
    private WebElement searchField;

    @FindBy(css = ".col-md-12")
    private WebElement onlineCashbackTitle;

    @FindBy(css = ".custom-control.custom-radio")
    private List<WebElement> filterList;

    @FindBy(css = ".col-6.col-lg-4.cashback-card")
    private List<WebElement> merchants;

    @FindBy(css = ".filteration .btn.btn-outline-primary.col")
    private WebElement clearBtn;


    public OnlineCashbackPage(WebDriver driver) {
        super(driver);
    }

    public void checkOnlineCashbackPageOpens(String selected) {
        waitVisibilityOfElement(onlineCashbackTitle);
        for (WebElement category : filterList) {
            WebElement selectedCheckbox = category.findElement(By.tagName("input"));
            if (selectedCheckbox.isSelected()) {
                Assert.assertEquals(getText(category),selected);
            }
            break;
        }
    }

    public int getMerchantFilteredList() throws InterruptedException {
        scrollToEndOfScreen();
        Thread.sleep(3000);
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
