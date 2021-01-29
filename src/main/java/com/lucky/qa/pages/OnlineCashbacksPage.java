package com.lucky.qa.pages;

import com.lucky.qa.commons.BasePage;
import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OnlineCashbacksPage extends BasePage {
    public OnlineCashbacksPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div/div[2]/div/div[1]/div/h3")
    private WebElement onlineCashbackTitle;

    @FindBy(xpath = "//div[1]/form/div/div")
    private List<WebElement> filterList;

    @FindBy(xpath = "//div/div[2]/div/div[2]/div[1]/div")
    private List<WebElement> merchants;

    public void checkOnlineCashbackPageOpens(String selected) {
        waitVisibilityOfElement(20, onlineCashbackTitle);
        for (WebElement category : filterList) {
            WebElement selectedCheckbox = category.findElement(By.tagName("input"));
            if (selectedCheckbox.isSelected() == true) {
                Assert.assertEquals(category.getText(), selected);

            }

            break;
        }

    }

    public int getMerchantFilteredList() throws InterruptedException {
        scrollToEndOfScreen();
        Thread.sleep(1000);
        List<WebElement> merchantCount = new ArrayList<>();
        for (WebElement merchant : merchants) {
            merchantCount.add(merchant);
        }
        return merchantCount.size();
    }
}
