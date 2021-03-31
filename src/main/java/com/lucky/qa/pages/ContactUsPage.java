package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends BasePage {
    @FindBy(id = "validationCustom01")
    private WebElement nameField;
    @FindBy(id = "exampleForm.ControlInput1")
    private WebElement mobileNumberField;
    @FindBy(id = "exampleForm.ControlSelect1")
    private WebElement subjectList;
    @FindBy(id = "exampleForm.ControlTextarea1")
    private WebElement tellUsMoreField;
    @FindBy(xpath = "//*[@class= 'robot-row ']//button")
    private WebElement sendBtn;
    @FindBy(xpath = "//h2")
    private WebElement successMessage;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void addMandatoryFields(String name, String mobileNumber, String text) {
        clearField(nameField);
        addText(nameField, name);
        clearField(mobileNumberField);
        addText(mobileNumberField, mobileNumber);
        clearField(tellUsMoreField);
        addText(tellUsMoreField, text);
    }

    public void selectSubject(int subjectTitle) {
        Select subject = new Select(subjectList);
        subject.selectByIndex(subjectTitle);
    }

    public void clickSendBtn() {
        clickButton(sendBtn);
    }

    public void checkSuccessMessage() throws InterruptedException {
        Thread.sleep(4000);
        Assert.assertEquals("Your message was sent successfully", successMessage.getText());
    }
}
