package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import com.lucky.qa.utilities.Helper;
import org.openqa.selenium.Keys;
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


    @FindBy(xpath = "//button[text() ='Save Changes']")
    private WebElement saveChangesBtn;

    @FindBy(xpath = "//button[text() ='Log out']")
    private WebElement logoutPopup;

    @FindBy(xpath = "//div/div/div[3]/button[1]")
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
        return loggedInEmail.getAttribute("value");
    }

    public void changeName() {
        nameField.sendKeys(Keys.COMMAND + "a");
        nameField.sendKeys(Keys.DELETE);
        addText(nameField, "QA " + helper.generateRandomName(5));
        clickButton(saveChangesBtn);
        waitVisibilityOfElement(toastMessage);
    }

    public void changePassword() {
        String newPassword = helper.generateRandomPassword(9);
        clickButton(passwordField);
        waitVisibilityOfElement(newPassHeader);
        helper.setPropertiesFileName("LoginData.properties");
        addText(oldPassField, helper.getValuesFromPropertiesFile("password"));
        addText(newPassField, newPassword);
        addText(newConfirmPassField, newPassword);
        helper.updateValueInPropertiesFile("password", newPassword);

        clickButton(saveChangesBtn);

        waitVisibilityOfElement(toastMessage);
    }

    public void clickLogout() {
        clickButton(logoutPopup);
        clickButton(logoutBtn);
    }
}
