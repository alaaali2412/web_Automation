package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.utilities.Helper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    Helper helper = new Helper();
    @FindBy(id = "formName")
    private WebElement nameField;

    @FindBy(id = "formEmail")
    private WebElement inputEmail;

    @FindBy(id = "formPassword")
    private WebElement inputPassword;

    @FindBy(id = "formRepeatPassword")
    private WebElement repeatPassField;

    @FindBy(xpath = "//form/div[2]/button")
    private WebElement copyEmail;

    @FindBy(xpath = "//*[@class='py-3']/button")
    private WebElement registerBtn;

    @FindBy(xpath = "//h1")
    private WebElement verificationHeader;

    @FindBy(xpath = "//*[@class='py-3']/div")
    private WebElement errorMessage;

    @FindBy(xpath = "//tbody//tbody/tr[2]/td/table/tbody//tbody/tr/td/a")
    private WebElement verifyEmailLink;

    @FindBy(xpath = "//section/div/div/div/a")
    private WebElement loginBtnAfterMailVerification;

    @FindBy(xpath = "//*[@class='inbox-dataList']//li[2]")
    private WebElement emailList;

    @FindBy(xpath = "//*[@class='inbox-dataList']//li[2]/div")
    private WebElement emailLink;

    @FindBy(xpath = "//p[1]/a")
    private WebElement registerLink;

    @FindBy(xpath = "//*[@id= 'formEmail']/following-sibling::div")
    private WebElement invalidEmailErrorMessage;

    @FindBy(xpath = "//*[@id= 'formPassword']/following-sibling::div")
    private WebElement invalidPassErrorMessage;

    @FindBy(xpath = "//*[@id= 'formRepeatPassword']/following-sibling::div")
    private WebElement confirmPassErrorMessage;

    @FindBy(xpath = "//*[@id= 'formName']/following-sibling::div")
    private WebElement nameFieldErrorMessage;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void addRegistrationName() {
        helper.setPropertiesFileName("RegistrationData.properties");
        addText(nameField, helper.getValuesFromPropertiesFile("Name"));
    }

    public void addNewEmail() {
        clearDefaultValueOfCopy(inputEmail);
        openNewTab();
        moveToTab(1);
        driver.get("https://10minemail.com/");
        waitForPageToLoad();
        clickButton(copyEmail);
        moveToTab(0);
        deleteTextInField(inputEmail);
        pasteTextInField(inputEmail);
        waitForTextInAttributeToBeExist(inputEmail.getAttribute("value"));
    }

    public void saveTheRegistrationEmail() {
        helper.setPropertiesFileName("RegistrationData.properties");
        helper.updateValueInPropertiesFile("RegistrationEmail", inputEmail.getAttribute("value"));
    }

    public void addRegistrationPasswords() {
        helper.setPropertiesFileName("RegistrationData.properties");
        String newPassword = Helper.generateRandomPassword(12);
        addText(inputPassword, newPassword);
        addText(repeatPassField, newPassword);
        helper.updateValueInPropertiesFile("RegistrationPassword", newPassword);
        helper.updateValueInPropertiesFile("RepeatPassword", newPassword);
    }

    public void clickRegisterBtn() {
        clickButton(registerBtn);
        waitVisibilityOfElement(verificationHeader);
    }

    public void checkUnverifiedMailErrorMessage(String errorMsg) {
        waitForTextToBeVisible(errorMessage);
        Assert.assertEquals(errorMsg, errorMessage.getText());
    }

    public void openVerificationMail() {
        moveToTab(1);
        while (!emailList.getText().contains("Email Confirmation")) {
            System.out.println("the text before " + emailList.getText());
            refreshCurrentPage();
        }
        clickButton(emailLink);
    }

    public void verifyRegistrationEmail() {
        scrollToEndOfScreen();
        clickButton(verifyEmailLink);
        moveToTab(2);
        waitVisibilityOfElement(verificationHeader);
        clickButton(loginBtnAfterMailVerification);
    }

    public void addInvalidName() {
        addText(nameField, "aa");
        deleteTextInField(nameField);
    }

    public void checkInvalidNameErrorMessage(String errorMsg) {
        Assert.assertEquals(errorMsg, nameFieldErrorMessage.getText());
    }

    public void registerWithInvalidEmailFormat() {
        addText(inputEmail, helper.generateRandomText(15) + "@.com");
    }

    public void checkInvalidEmailErrorMessage(String errorMsg) {
        Assert.assertEquals(errorMsg, invalidEmailErrorMessage.getText());
    }

    public void registerWithPassLessThanSixCharacters(String errorMsg) {
        addText(inputPassword, helper.generateRandomText(3));
        clickButton(repeatPassField);
        Assert.assertEquals(errorMsg, invalidPassErrorMessage.getText());
    }

    public void registerWithInvalidPassFormat() {
        addText(inputPassword, helper.generateRandomText(3));
        clickButton(repeatPassField);
    }

    public void checkInvalidPassErrorMessage(String errorMsg) {
        Assert.assertEquals(errorMsg, invalidPassErrorMessage.getText());
    }

    public void registerWithInvalidRepeatPass() {
        addText(repeatPassField, "aa");
    }

    public void checkRepeatPassErrorMessage(String errorMsg) {
        Assert.assertEquals(errorMsg, confirmPassErrorMessage.getText());
    }
}
