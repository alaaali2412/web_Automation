package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class OfferDetailsPage extends BasePage {
    public OfferDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".btn.btn-primary")
    private WebElement getOfferBtn;

    @FindBy(css = ".col-lg-7 > div:nth-child(1) > h3")
    private WebElement popupHeader;

    @FindBy(css = ".close")
    private WebElement popupCloseBtn;

    public void clickGetOffer() {
        waitVisibilityOfElement(getOfferBtn);
        clickButton(getOfferBtn);
    }

    public void closePopUP(String headerText) {
        waitVisibilityOfElement(popupHeader);
        Assert.assertEquals(getText(popupHeader),headerText);
        clickButton(popupCloseBtn);
    }
}
