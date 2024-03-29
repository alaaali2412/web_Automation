package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.utilities.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegistrationPage extends BasePage {
    static String newPassword;
    static String newEmail;
    Helper helper = new Helper();

    @FindBy(id = "formName")
    private WebElement nameField;

    @FindBy(id = "formEmail")
    private WebElement inputEmail;

    @FindBy(id = "formPassword")
    private WebElement inputPassword;

    @FindBy(id = "formRepeatPassword")
    private WebElement repeatPassField;

    @FindBy(css = ".input-box-col.hidden-xs-sm")
    private WebElement copyEmail;

    @FindBy(xpath = "//*[@id='counter_sec_one']/span")
    private WebElement secondCounter;

    @FindBy(css = ".btn.btn-block.btn-primary")
    private WebElement registerBtn;

    @FindBy(css = ".mailVerification-title")
    private WebElement verificationHeader;

    @FindBy(css = ".error.error-message.text-center")
    private WebElement errorMessage;

    @FindBy(css = "tr:nth-child(2)>td>table>tbody>tr>td>table>tbody>tr>td>a")
    private WebElement verifyEmailLink;

    @FindBy(css = ".btn.btn-primary.btn-block")
    private WebElement loginBtnAfterMailVerification;

    @FindBy(css = " ul > li:nth-child(2)  div.col-box.hidden-xs-sm ")
    private WebElement emailList;

    @FindBy(xpath = "//*[@class='small_subject']")
    private WebElement emailLink;

    @FindBy(css = ".form-group:nth-child(2)>.invalid-feedback")
    private WebElement invalidEmailErrorMessage;

    @FindBy(css = ".form-group:nth-child(3)>.invalid-feedback")
    private WebElement invalidPassErrorMessage;

    @FindBy(css = ".form-group:nth-child(4)>.invalid-feedback")
    private WebElement confirmPassErrorMessage;

    @FindBy(css = ".form-group:nth-child(1)>.invalid-feedback")
    private WebElement nameFieldErrorMessage;

    @FindBy(css = ".btn.btn-block.btn-primary")
    private WebElement loginBtn;

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
        forceClickElement(copyEmail);
        moveToTab(0);
        deleteTextInField(inputEmail);
        pasteTextInField(inputEmail);
        waitForTextInAttributeToBeExist(inputEmail.getAttribute("value"));
        newEmail = inputEmail.getAttribute("value");
    }

    public void loginWithUnregisteredEmail() {
        waitVisibilityOfElement(inputPassword);
        clearField(inputEmail);
        addText(inputEmail, newEmail);
        waitForTextInAttributeToBeExist("value");
        clearField(inputPassword);
        addText(inputPassword, newPassword);
        clickButton(loginBtn);
    }

    public void saveTheRegistrationDetails() {
        helper.setPropertiesFileName("RegistrationData.properties");
        helper.updateValueInPropertiesFile("RegistrationEmail", newEmail);
        helper.updateValueInPropertiesFile("RegistrationPassword", newPassword);
        helper.updateValueInPropertiesFile("RepeatPassword", newPassword);
    }

    public void addRegistrationPasswords() {
        helper.setPropertiesFileName("RegistrationData.properties");
        newPassword = helper.generateRandomPassword(12);
        addText(inputPassword, newPassword);
        addText(repeatPassField, newPassword);
    }

    public void clickRegisterBtn() {
        clickButton(registerBtn);
        waitVisibilityOfElement(verificationHeader);
    }

    public void checkUnverifiedMailErrorMessage(String errorMsg) {
        waitForTextToBeVisible(errorMessage);
        Assert.assertEquals( errorMessage.getText(), errorMsg);
    }

    public void openVerificationMail() {
        moveToTab(1);
        while (!emailList.getText().contains("Email Confirmation")) {
            driverWait(60);
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
        confirmAction(nameField);
    }

    public void checkInvalidNameErrorMessage(String errorMsg) {
        Assert.assertEquals( nameFieldErrorMessage.getText(), errorMsg);
    }

    public void registerWithInvalidEmailFormat() {
        addText(inputEmail, helper.generateRandomText(15) + "@.com");
    }

    public void checkInvalidEmailErrorMessage(String errorMsg) {
        Assert.assertEquals( invalidEmailErrorMessage.getText(),errorMsg);
    }

    public void registerWithPassLessThanSixCharacters(String errorMsg) {
        addText(inputPassword, helper.generateRandomText(3));
        clickButton(repeatPassField);
        Assert.assertEquals( invalidPassErrorMessage.getText(),errorMsg);
    }

    public void registerWithInvalidPassFormat() {
        addText(inputPassword, helper.generateRandomText(3));
        clickButton(repeatPassField);
    }

    public void checkInvalidPassErrorMessage(String errorMsg) {
        Assert.assertEquals( invalidPassErrorMessage.getText(),errorMsg);
    }

    public void registerWithInvalidRepeatPass() {
        addText(repeatPassField, "aa");
    }

    public void checkRepeatPassErrorMessage(String errorMsg) {
        Assert.assertEquals( confirmPassErrorMessage.getText(),errorMsg);
    }
}
