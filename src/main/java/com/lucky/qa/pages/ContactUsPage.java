package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {


    @FindBy(xpath = "//h2")
    private WebElement title;

    @FindBy(xpath = "//h4[1]")
    private WebElement textMessage;

    @FindBy(xpath = "//h4[2]")
    private WebElement supportEmail;

    @FindBy(xpath = "//*[@class = 'success-img']")
    private WebElement successImage;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void checkContactUsTexts(String titleTxt, String textMsg, String email) {
        Assert.assertEquals(title.getText(), titleTxt);
        Assert.assertEquals(textMessage.getText(), textMsg);
        Assert.assertEquals(supportEmail.getText(), email);
    }

}
