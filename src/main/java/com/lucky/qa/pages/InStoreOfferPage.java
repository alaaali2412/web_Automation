package com.lucky.qa.pages;


import com.lucky.qa.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class InStoreOfferPage extends BasePage {

    @FindBy(className = "InstoreOffer")
    private WebElement card;

    @FindBy(className = "btn-primary")
    private WebElement  getOfferButton;

    @FindBy(className = "modal-content")
    private WebElement modalOffers;

    @FindBy(xpath = "//*/div/div[2]/div/div[3]/div[1]")
    private List <WebElement> inStoreItems;

    @FindBy(xpath = "//div[2]/button")
    private WebElement getOfferBtn;

    public InStoreOfferPage(WebDriver driver) {
        super(driver);
    }

    public void selectCard(){
        clickButton(card);
    }

    public void getOffer(){
        clickButton(getOfferButton);
    }

    public void checkModalOffer(){
        Assert.assertEquals(true, modalOffers.isDisplayed());
        System.out.println("Modal appear");
    }

    public void selectInStoreItem() throws InterruptedException {
        for (WebElement item : inStoreItems){
            Thread.sleep(3000);
            clickButton(item);
            break;
        }
    }
}
