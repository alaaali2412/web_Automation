package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class OfferDetailsPage extends BasePage {
    public OfferDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[2]/button")
    private WebElement getOfferBtn;

    @FindBy(xpath = "//div[2]/div[1]/h3")
    private WebElement popupHeader;

    @FindBy(xpath = "//*[@type = 'button']")
    private WebElement popupCloseBtn;

    public void clickGetOffer() {
        waitVisibilityOfElement(getOfferBtn);
        clickButton(getOfferBtn);

    }

    public void closePopUP() {
        waitVisibilityOfElement(popupHeader);
        Assert.assertEquals(getText(popupHeader), "Download Lucky App to enjoy in-store and delivery discounts!");
        clickButton(popupCloseBtn);


    }

}
