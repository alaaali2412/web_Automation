package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import com.lucky.qa.connectors.DriverFactory;
import com.lucky.qa.utilities.Helper;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class LoginPage extends BasePage {

    static String parentWindow;
    Helper helper = new Helper();
    @FindBy(className = "btn-icon")
    private List<WebElement> listItems;

    @FindBy(className = "btn-inner--text")
    private WebElement facebookButton;

    @FindBy(id = "email")
    private WebElement inputEmailFb;

    @FindBy(id = "pass")
    private WebElement inputPasswordFb;

    @FindBy(xpath = "//*[@id='u_0_0']")
    private WebElement loginBtnFb;

    @FindBy(xpath = "//*[@id='buttons']")
    private WebElement continueBtnFb;

    @FindBy(id = "formName")
    private WebElement nameField;

    @FindBy(id = "formEmail")
    private WebElement inputEmail;

    @FindBy(id = "formPassword")
    private WebElement inputPassword;

    @FindBy(id = "formRepeatPassword")
    private WebElement repeatPassField;

    @FindBy(xpath = "//div[3]/button")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@Class='py-3']/button")
    private WebElement registerBtn;

    @FindBy(id = "identifierId")
    private WebElement googleEmail;

    @FindBy(id = "password")
    private WebElement googlePassword;

    @FindBy(id = "identifierNext")
    private WebElement emailNextBtn;

    @FindBy(className = "modal-open")
    private WebElement modal;

    @FindBy(id = "passwordNext")
    private WebElement passNextBtn;

    @FindBy(className = "carousel-inner")
    private WebElement homeBanner;

    @FindBy(xpath = "//div/header/div[1]/nav/div/div[7]/a")
    private WebElement signInBtn;

    @FindBy(xpath = "//*[@class='py-3']/div")
    private WebElement errorMessage;

    @FindBy(xpath = " //*[@class='form-footer']//p[2]")
    private WebElement forgetPassLink;

    @FindBy(xpath = "//button[text() ='Email me']")
    private WebElement emailMeBtn;

    @FindBy(xpath = "//*[@class ='mailVerification-text']")
    private WebElement mailVerificationText;

    @FindBy(xpath = "//div[4]/ul[2]/li[3]/a")
    private WebElement googleSignIn;

    @FindBy(xpath = "//*[@class = 'F cf zt']/tbody/tr")
    private List<WebElement> googleEmails;

    @FindBy(xpath = "//*[@class='bA4']//span[1]")
    private WebElement emailHeader;

    @FindBy(xpath = "//*[@class='y6']//span[1]")
    private WebElement emailTitle;

    @FindBy(xpath = "//table//table/tbody/tr[5]/td/a/table/tbody/tr/td")
    private WebElement resetPassLink;

    @FindBy(id = "formBasicPassword1")
    private WebElement newPassField;

    @FindBy(id = "formBasicPassword2")
    private WebElement confirmNewPassField;

    @FindBy(xpath = "//div[3]/button")
    private WebElement saveChangesBtn;

    @FindBy(xpath = "//*[@class = 'react-toast-notifications__toast__content css-1ad3zal']")
    private WebElement toastMessage;

    @FindBy(xpath = "//div[2]/div/div/input[2]")
    private WebElement createEmailBtn;

    @FindBy(id = "userTempMail")
    private WebElement copyEmail;

    @FindBy(xpath = "//h1")
    private WebElement verificationHeader;

    @FindBy(id = "divEmailList")
    private WebElement emailList;

    @FindBy(xpath = "//tbody//tbody/tr[2]/td/table/tbody//tbody/tr/td/a")
    private WebElement verifyEmailLink;

    @FindBy(xpath = "//section/div/div/div/a")
    private WebElement loginBtnAfterMailVerification;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void addRegistrationName() {
        helper.setPropertiesFileName("RegistrationData.properties");
        addText(nameField, helper.getValuesFromPropertiesFile("Name"));
    }

    public void addRegistrationEmail() throws InterruptedException {
        helper.setPropertiesFileName("RegistrationData.properties");
        //To Clear the copy
        addText(inputEmail, " ");
        inputEmail.sendKeys(Keys.COMMAND + "a");
        inputEmail.sendKeys(Keys.COMMAND + "c");
        openNewTab();
        driver.get("http://www.20minutemail.com/");
        clickButton(createEmailBtn);
        clickButton(copyEmail);
        driver.switchTo().window(tabs.get(0));
        inputEmail.sendKeys(Keys.COMMAND + "v");
        Thread.sleep(1000);
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

    public void checkUnverifiedMailErrorMessage() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertEquals("Please verify your email", errorMessage.getText());
    }

    public void openVerificationMail() throws InterruptedException {
        driver.switchTo().window(tabs.get(1));
        while (!emailList.getText().contains("Email Confirmation")) {
            Thread.sleep(5000);
            driver.navigate().refresh();
            if (emailList.getText().contains("Email Confirmation")) {
                clickButton(emailList);
                break;
            }
        }
    }

    public void verifyRegistrationEmail() {
        scrollToEndOfScreen();
        clickButton(verifyEmailLink);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        waitVisibilityOfElement(verificationHeader);
        clickButton(loginBtnAfterMailVerification);
    }

    public String login(String fileName, String email, String password) {
        helper.setPropertiesFileName(fileName);
        waitVisibilityOfElement(inputPassword);
        clearField(inputEmail);
        addText(inputEmail, helper.getValuesFromPropertiesFile(email));
        clearField(inputPassword);
        addText(inputPassword, helper.getValuesFromPropertiesFile(password));
        clickButton(loginBtn);
        return helper.getValuesFromPropertiesFile(email);
    }

    public void loginFacebook(String email, String password) {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindow = driver.getWindowHandles();
        for (String child : allWindow) {
            if (!parentWindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                clearField(inputEmailFb);
                addText(inputEmailFb, email);
                clearField(inputPasswordFb);
                addText(inputPasswordFb, password);
                waitVisibilityOfElement(loginBtn);
                clickButton(loginBtnFb);
            }
        }
        DriverFactory.getDriver().switchTo().window(parentWindow);
        waitVisibilityOfElement(signInBtn);
    }

    public String loginGoogle(String email, String password) {
        parentWindow = driver.getWindowHandle();
        Set<String> allWindow = driver.getWindowHandles();
        for (String child : allWindow) {
            if (!parentWindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                forceAddText(googleEmail, email);
                clickButton(emailNextBtn);
                waitVisibilityOfElement(googlePassword);
                forceAddText(googlePassword, password);
                clickButton(passNextBtn);
            }
        }
        return email;
    }

    public void getBackToMainWindow() throws InterruptedException {
        driver.switchTo().window(parentWindow);
        Thread.sleep(9000);
        DriverFactory.getDriver().navigate().refresh();
    }

    public void loginWithInvalidPass() throws InterruptedException {
        helper.setPropertiesFileName("LoginData.properties");
        waitVisibilityOfElement(inputPassword);
        clearField(inputEmail);
        addText(inputEmail, helper.getValuesFromPropertiesFile("GoogleEmail"));
        clearField(inputPassword);
        addText(inputPassword, helper.getValuesFromPropertiesFile("WrongPassword"));
        clickButton(loginBtn);
        Thread.sleep(2000);
    }

    public void checkErrorMessageIsDisplayed() {
        Assert.assertEquals("Email or Password is incorrect", errorMessage.getText());
    }

    public void clickForgetPasswordLink() {
        clickButton(forgetPassLink);
    }

    public void sendEmailResetPass() {
        helper.setPropertiesFileName("LoginData.properties");
        clearField(inputEmail);
        addText(inputEmail, helper.getValuesFromPropertiesFile("GoogleEmail"));
        clickButton(emailMeBtn);
        System.out.println(mailVerificationText.isDisplayed());
        waitVisibilityOfElement(mailVerificationText);
    }

    public void OpenGmail() {
        helper.setPropertiesFileName("LoginData.properties");
        driver.navigate().to("https://mail.google.com/");
        clickButton(googleSignIn);
        loginGoogle(helper.getValuesFromPropertiesFile("GoogleEmail"),
                helper.getValuesFromPropertiesFile("GoogleEmailPassword"));
    }

    public void openResetPassEmail() {
        waitVisibilityOfAllElements(googleEmails);
        for (WebElement email : googleEmails) {
            if (emailHeader.getAttribute("name").equals("Lucky") &&
                    emailTitle.getText().contains("Password Reset Link")) {
                forceClickElement(email);
                waitVisibilityOfElement(resetPassLink);
                forceClickElement(resetPassLink);
                break;
            }
        }
    }

    public void addNewPass() {
        helper.setPropertiesFileName("LoginData.properties");
        String newPassword = Helper.generateRandomPassword(9);
        ArrayList<String> chromeTabs = new ArrayList<>(driver.getWindowHandles());
        DriverFactory.getDriver().switchTo().window(chromeTabs.get(2));
        forceAddText(newPassField, newPassword);
        forceAddText(confirmNewPassField, newPassword);
        clickButton(saveChangesBtn);
        waitVisibilityOfElement(toastMessage);
        helper.updateValueInPropertiesFile("NewPassword", newPassword);
    }
}

