package com.lucky.qa.pages;


import com.lucky.qa.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class InStoreOfferPage extends BasePage {

    @FindBy(className = "InstoreOffer")
    WebElement card;

    @FindBy(className = "btn-primary")
    WebElement  getOfferButton;

    @FindBy(className = "modal-content")
    WebElement modalOffers;

    public InStoreOfferPage(WebDriver driver) {
        super(driver);
    }

    public void selectCard(){
        click(card);
    }

    public void getOffer(){
        click(getOfferButton);
    }

    public void checkModalOffer(){
        Assert.assertEquals(true, modalOffers.isDisplayed());
        System.out.println("Modal appear");
    }

}
