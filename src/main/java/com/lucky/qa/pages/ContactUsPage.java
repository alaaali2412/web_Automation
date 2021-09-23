package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {

    @FindBy(css = "h2.h2-text")
    private WebElement title;

    @FindBy(css = "h4.h4-text:nth-child(2)")
    private WebElement textMessage;

    @FindBy(css = "h4.h4-text:nth-child(3)")
    private WebElement supportEmail;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void checkContactUsTexts(String titleTxt, String textMsg, String email) {
        Assert.assertEquals(title.getText(),titleTxt);
        Assert.assertEquals(textMessage.getText() ,textMsg);
        Assert.assertEquals(supportEmail.getText(),email );
    }
}
