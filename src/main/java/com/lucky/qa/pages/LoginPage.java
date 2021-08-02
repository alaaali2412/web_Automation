package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.connectors.DriverFactory;
import com.lucky.qa.utilities.Helper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class LoginPage extends BasePage {

    Helper helper = new Helper();
    @FindBy(id = "formEmail")
    private WebElement inputEmail;

    @FindBy(id = "formPassword")
    private WebElement inputPassword;

    @FindBy(id = "email")
    private WebElement inputEmailFb;

    @FindBy(id = "pass")
    private WebElement inputPasswordFb;

    @FindBy(xpath = "//*[@id='u_0_0']")
    private WebElement loginBtnFb;

    @FindBy(xpath = "//div[3]/button")
    private WebElement loginBtn;

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

    @FindBy(xpath = "//*[@class='btn btn-block btn-primary']")
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

    @FindBy(xpath = "//*[@src= '//ssl.gstatic.com/ui/v1/icons/mail/images/cleardot.gif']")
    private List<WebElement> expandEmailBtn;

    @FindBy(xpath = "//table//table/tbody/tr[5]/td/a/table/tbody/tr/td")
    private List<WebElement> resetPassLinks;

    @FindBy(id = "formBasicPassword1")
    private WebElement newPassField;

    @FindBy(id = "formBasicPassword2")
    private WebElement confirmNewPassField;

    @FindBy(xpath = "//div[3]/button")
    private WebElement saveChangesBtn;

    @FindBy(xpath = "//*[@class = 'react-toast-notifications__toast__content css-1ad3zal']")
    private WebElement toastMessage;

    @FindBy(xpath = "//h1")
    private WebElement verificationHeader;

    @FindBy(xpath = "//*[@id= 'formEmail']/following-sibling::div")
    private WebElement invalidEmailErrorMessage;

    @FindBy(xpath = "//section[3]/div/div/div/div[1]/h2")
    private WebElement newsLetterheader;

    @FindBy(xpath = "//*[@class='gb_Ca gbii']")
    private WebElement googleProfileIcon;

    @FindBy(id = "gb_71")
    private WebElement googleLogoutBtn;

    @FindBy(xpath = "//section//div/ul/li[3]/div")
    private WebElement googleRemoveAccountBtn;

    @FindBy(xpath = "//settings-ui")
    private WebElement clearDataWindow;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String login(String fileName, String email, String password) {
        helper.setPropertiesFileName(fileName);
        waitVisibilityOfElement(inputPassword);
        clearField(inputEmail);
        addText(inputEmail, helper.getValuesFromPropertiesFile(email));
        waitForTextInAttributeToBeExist("value");
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
                waitForPageToLoad();
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

    public String loginGoogle(String email, String password) {
        String parentWindow = DriverFactory.getDriver().getWindowHandle();
        Set<String> allWindow = DriverFactory.getDriver().getWindowHandles();
        for (String child : allWindow) {
            if (!parentWindow.equalsIgnoreCase(child)) {
                DriverFactory.getDriver().switchTo().window(child);
                forceAddText(googleEmail, email);
                clickButton(emailNextBtn);
                DriverFactory.getDriver().switchTo().window(child);
                waitVisibilityOfElement(googlePassword);
                forceAddText(googlePassword, password);
                clickButton(passNextBtn);
            }
        }
        driver.switchTo().window(parentWindow);
        return email;
    }

    public void loginWithInvalidPass() {
        helper.setPropertiesFileName("LoginData.properties");
        waitVisibilityOfElement(inputPassword);
        deleteTextInField(inputEmail);
        addText(inputEmail, helper.getValuesFromPropertiesFile("GoogleEmail"));
        clearField(inputPassword);
        addText(inputPassword, helper.getValuesFromPropertiesFile("WrongPassword"));
        clickButton(loginBtn);
    }

    public void checkErrorMessageIsDisplayed(String errorMsg) {
        waitForTextToBeVisible(errorMessage);
        Assert.assertEquals(errorMsg, errorMessage.getText());
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
        waitForPageToLoad();
        clickButton(googleSignIn);
        moveToTab(1);
        addGmailCredentials(helper.getValuesFromPropertiesFile("GoogleEmail"),
                helper.getValuesFromPropertiesFile("GoogleEmailPassword"));
        waitForPageToLoad();
    }

    public void checkTheUnreadEmails() {
        moveToTab(1);
        waitVisibilityOfAllElements(unreadEmails);
        for (WebElement email : unreadEmails) {
            while (!emailTitle.getAttribute("name").equals("Lucky") &&
                    !emailSubject.getText().contains("Password Reset Link")) {
                driverWait(30);
                refreshCurrentPage();
            }
            forceClickElement(email);
            driverWait(30);
            break;
        }
    }

    public void openResetPassEmail() {
        waitForPageToLoad();
        for (int i = 0 ; i<resetPassLinks.size(); i++){
            if (!resetPassLinks.get(resetPassLinks.size()-1).isDisplayed()) {
                waitVisibilityOfAllElements(expandEmailBtn);
                clickButton(expandEmailBtn.get(expandEmailBtn.size() - 1));
                moveToTab(1);
                waitVisibilityOfElement(resetPassLinks.get(resetPassLinks.size()-1));
                forceClickElement(resetPassLinks.get(resetPassLinks.size()-1));
            } else {
                waitVisibilityOfElement(resetPassLinks.get(resetPassLinks.size()-1));
                forceClickElement(resetPassLinks.get(resetPassLinks.size()-1));
            }
        }

    }

    public void addNewPass() {
        helper.setPropertiesFileName("LoginData.properties");
        String newPassword = helper.generateRandomPassword(9);
        moveToTab(2);
        forceAddText(newPassField, newPassword);
        forceAddText(confirmNewPassField, newPassword);
        clickButton(saveChangesBtn);
        waitVisibilityOfElement(toastMessage);
        helper.updateValueInPropertiesFile("NewPassword", newPassword);
    }

    public void logOutGmail()  {
        driver.close();
        moveToTab(1);
        forceClickElement(googleProfileIcon);
        forceClickElement(googleLogoutBtn);
        waitForPageToLoad();
        forceClickElement(googleRemoveAccountBtn);
        driver.close();
        moveToTab(0);
    }

    public void addInvalidEmailFormat() {
        waitVisibilityOfElement(inputPassword);
        addText(inputEmail, helper.generateRandomText(9) + "@.com");
        clickButton(inputPassword);
    }

    public void checkInvalidEmailErrorMessage(String errorMsg) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitForTextToBeVisible(invalidEmailErrorMessage);
        Assert.assertEquals(errorMsg, invalidEmailErrorMessage.getText());
    }

}

