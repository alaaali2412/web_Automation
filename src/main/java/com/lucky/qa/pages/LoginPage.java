package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import com.lucky.qa.connectors.DriverFactory;
import com.lucky.qa.utilities.Helper;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;


public class LoginPage extends BasePage {

    Helper helper = new Helper();
    @FindBy(id = "email")
    private WebElement inputEmailFb;

    @FindBy(id = "pass")
    private WebElement inputPasswordFb;

    @FindBy(xpath = "//*[@id='u_0_0']")
    private WebElement loginBtnFb;

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

    @FindBy(xpath = "//p[1]/a")
    private WebElement registerLink;

    @FindBy(xpath = "//*[text() = 'Register']")
    private WebElement registerBtn;

    @FindBy(id = "identifierId")
    private WebElement googleEmail;

    @FindBy(id = "password")
    private WebElement googlePassword;

    @FindBy(id = "identifierNext")
    private WebElement emailNextBtn;

    @FindBy(id = "passwordNext")
    private WebElement passNextBtn;

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

    @FindBy(xpath = "//*[@class='y6']//*[@class='bqe']")
    private List<WebElement> unreadEmails;

    @FindBy(xpath = "//*[@class='bA4']//span[1]")
    private WebElement emailTitle;

    @FindBy(xpath = "//*[@class='y6']//span[1]")
    private WebElement emailSubject;

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

    @FindBy(xpath = "//form/div[2]/button")
    private WebElement copyEmail;

    @FindBy(xpath = "//h1")
    private WebElement verificationHeader;

    @FindBy(xpath = "//*[@class='inbox-dataList']//li[2]")
    private WebElement emailList;

    @FindBy(xpath = "//*[@class='inbox-dataList']//li[2]/div")
    private WebElement emailLink;

    @FindBy(xpath = "//tbody//tbody/tr[2]/td/table/tbody//tbody/tr/td/a")
    private WebElement verifyEmailLink;

    @FindBy(xpath = "//section/div/div/div/a")
    private WebElement loginBtnAfterMailVerification;

    @FindBy(xpath = "//*[@id= 'formEmail']/following-sibling::div")
    private WebElement invalidEmailErrorMessage;

    @FindBy(xpath = "//*[@id= 'formPassword']/following-sibling::div")
    private WebElement invalidPassErrorMessage;

    @FindBy(xpath = "//*[@class='ajV']//div")
    private WebElement expandEmailBtn;

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
        moveToTab(1);
        driver.get("https://10minemail.com/");
        clickButton(copyEmail);
        moveToTab(0);
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
        moveToTab(1);
        System.out.println("the text before " + emailList.getText());
        while (!emailList.getText().contains("Email Confirmation")) {
            System.out.println("the text before " + emailList.getText());
            Thread.sleep(5000);
            driver.navigate().refresh();
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

    public void addGmailCredentials(String email, String password) {
        forceAddText(googleEmail, email);
        clickButton(emailNextBtn);
        waitVisibilityOfElement(googlePassword);
        forceAddText(googlePassword, password);
        clickButton(passNextBtn);
    }

    public String loginGoogle(String email, String password) throws InterruptedException {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindow = driver.getWindowHandles();
        for (String child : allWindow) {
            if (!parentWindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                addGmailCredentials(email, password);
            }
        }
        driver.switchTo().window(parentWindow);
        Thread.sleep(9000);
        DriverFactory.getDriver().navigate().refresh();
        return email;
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
        waitVisibilityOfElement(mailVerificationText);
    }

    public void OpenGmail() {
        helper.setPropertiesFileName("LoginData.properties");
        driver.navigate().to("https://mail.google.com/");
        clickButton(googleSignIn);
        moveToTab(1);
        addGmailCredentials(helper.getValuesFromPropertiesFile("GoogleEmail"),
                helper.getValuesFromPropertiesFile("GoogleEmailPassword"));
    }

    public void checkTheUnreadEmails() {
        waitVisibilityOfAllElements(unreadEmails);
        for (WebElement email : unreadEmails) {
            if (emailTitle.getAttribute("name").equals("Lucky") &&
                    emailSubject.getText().contains("Password Reset Link")) {
                forceClickElement(email);
                break;
            }
        }
    }

    public void openResetPassEmail() throws InterruptedException {
        Thread.sleep(2000);
        if (!resetPassLink.isDisplayed()) {
            waitVisibilityOfElement(expandEmailBtn);
            forceClickElement(expandEmailBtn);
            waitVisibilityOfElement(resetPassLink);
            forceClickElement(resetPassLink);
        } else {
            waitVisibilityOfElement(resetPassLink);
            forceClickElement(resetPassLink);
        }
    }

    public void addNewPass() {
        helper.setPropertiesFileName("LoginData.properties");
        String newPassword = Helper.generateRandomPassword(9);
        moveToTab(2);
        forceAddText(newPassField, newPassword);
        forceAddText(confirmNewPassField, newPassword);
        clickButton(saveChangesBtn);
        waitVisibilityOfElement(toastMessage);
        helper.updateValueInPropertiesFile("NewPassword", newPassword);
    }

    public void addInvalidEmailFormat() {
        waitVisibilityOfElement(inputPassword);
        addText(inputEmail, helper.generateRandomText(9) + "@.com");
        clickButton(inputPassword);
    }

    public void checkInvalidEmailErrorMessage() {
        Assert.assertEquals("Please enter a valid email", invalidEmailErrorMessage.getText());
    }

    public void clickRegisterLink() {
        clickButton(registerLink);
        waitVisibilityOfElement(repeatPassField);
    }

    public void registerWithInvalidPassFormat() throws InterruptedException {
        addRegistrationEmail();
        addText(inputPassword, helper.generateRandomText(6));
        clickButton(repeatPassField);
    }

    public void checkInvalidPassErrorMessage() {
        Assert.assertEquals("Password must contains a number or symbol", invalidPassErrorMessage.getText());

    }
}

