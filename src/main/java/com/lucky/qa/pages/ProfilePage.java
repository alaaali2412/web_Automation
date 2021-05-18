package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.utilities.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    Helper helper = new Helper();

    @FindBy(id = "exampleForm.ControlInput1")
    private WebElement loggedInEmail;

    @FindBy(id = "validationCustom01")
    private WebElement nameField;

    @FindBy(id = "formBasicPassword")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class = 'py-3 text-center']")
    private WebElement saveChangesBtn;

    @FindBy(xpath = "//*[@class= 'modal-footer']/button[1]")
    private WebElement logoutPopup;

    @FindBy(xpath = "//*[@class = 'd-flex justify-content-between']//button")
    private WebElement logoutBtn;

    @FindBy(xpath = "//*[@class = 'react-toast-notifications__toast__content css-1ad3zal']")
    private WebElement toastMessage;

    @FindBy(id = "formBasicPassword0")
    private WebElement oldPassField;

    @FindBy(id = "formBasicPassword1")
    private WebElement newPassField;

    @FindBy(id = "formBasicPassword2")
    private WebElement newConfirmPassField;

    @FindBy(tagName = "h2")
    private WebElement newPassHeader;

    public String getLoggedInEmail() {
        waitVisibilityOfElement(loggedInEmail);
        return loggedInEmail.getAttribute("value");
    }

    public void changeName() {
        deleteTextInField(nameField);
        addText(nameField, "QA " + helper.generateRandomText(5));
        clickButton(saveChangesBtn);
        waitVisibilityOfElement(toastMessage);
    }

    public void changePassword() {
        String newPassword = Helper.generateRandomPassword(12);
        clickButton(passwordField);
        waitVisibilityOfElement(newPassHeader);
        helper.setPropertiesFileName("LoginData.properties");
        addText(oldPassField, helper.getValuesFromPropertiesFile("password"));
        addText(newPassField, newPassword);
        addText(newConfirmPassField, newPassword);
        clickButton(saveChangesBtn);
        waitVisibilityOfElement(toastMessage);
        helper.updateValueInPropertiesFile("password", newPassword);
    }

    public void clickLogout() {
        clickButton(logoutBtn);
        clickButton(logoutPopup);
    }
}
