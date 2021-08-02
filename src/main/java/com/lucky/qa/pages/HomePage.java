package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.utilities.DatabaseHelper;
import com.lucky.qa.utilities.Helper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@class ='px-xl-3 px-lg-2 nav-link-item']//*[@href='/SignIn']")
    private WebElement signInBtn;

    @FindBy(xpath = "//div[2]/div/div[2]/button[2]/span")
    private WebElement facebookBtn;

    @FindBy(xpath = "//*/div[2]//div[2]/button[1]")
    private WebElement googleBtn;

    @FindBy(xpath = "//*[@href='/SignUp']")
    private WebElement emailBtn;

    @FindBy(xpath = "//div[1]/nav/div/div[2]")
    private WebElement inStoreBtn;

    @FindBy(xpath = "//*[@class ='px-xl-3 px-lg-2 nav-link-item']//*[@class='nav-link-item dropdown nav-item']")
    private WebElement profileDropdown;

    @FindBy(xpath = "//*[@href='/Profile']")
    private WebElement profileBtn;

    @FindBy(xpath = "//*[@class='dropdown-item'][2]")
    private WebElement logOutBtn;

    @FindBy(id = "exampleForm.ControlInput1")
    private WebElement loggedInEmail;

    @FindBy(id = "basic-nav-dropdown")
    private WebElement onlineCashbackBtn;

    @FindBy(xpath = "//nav/div/div[1]/div/div/a")
    private List<WebElement> onlineCashbackList;

    @FindBy(className = "//header/div[1]/nav//div/span")
    private WebElement logoBtn;

    @FindBy(xpath = "//*[@class ='px-xl-3 px-lg-2 nav-link-item']//*[@href='/Wallet']")
    private WebElement walletBtn;

    @FindBy(className = "wallet-body")
    private WebElement transactionSection;

    @FindBy(className = "hero-banner")
    private WebElement homeBanner;

    @FindBy(xpath = "//*[@class = 'container-md']/a")
    private WebElement homeScreenBtn;

    @FindBy(xpath = "//*/ul/li[2]/a")
    private WebElement contactUsBtn;

    @FindBy(id = "formEmail")
    private WebElement newsLetterEmailField;

    @FindBy(xpath = "//*[@class = 'row']//*[@type='submit']")
    private WebElement subscribeBtn;

    @FindBy(xpath = "//*[@class = 'success success-message']")
    private WebElement successMessage;

    @FindBy(xpath = "//h1")
    private WebElement headingText;

    @FindBy(xpath = "//*[@class='close-modal']")
    private List<WebElement> popupCloseBtn;

    @FindBy(xpath = "//*[@class ='px-xl-3 px-lg-2 nav-link-item']//*[@class='language-switcher']")
    private WebElement languageBtn;

    @FindBy(xpath = "//*[@class = 'container-md']//div[8]")
    private WebElement registerBtn;

    @FindBy(css = "#__next > div > header > div:nth-child(1) > nav > div > span")
    private WebElement luckyBrandLogo;

    public void clickContactUsBtn() {
        clickButton(contactUsBtn);
        driverWait(30);
        refreshCurrentPage();
    }

    public void openHomeScreen() {
        clickButton(homeScreenBtn);
        checkIfPopUpExist();
    }

    public void clickSignInBtn() {
        waitVisibilityOfElement(signInBtn);
        clickButton(signInBtn);
    }

    public void facebookLogin() {
        clickButton(facebookBtn);
    }

    public void googleLogin() {
        driver.manage().deleteAllCookies();
        waitVisibilityOfElement(googleBtn);
        forceClickElement(googleBtn);
    }

    public void clickSignupBtn() {
        waitVisibilityOfElement(emailBtn);
        clickButton(emailBtn);
        waitForPageToLoad();
    }

    public void clickInStoreBtn() {
        waitVisibilityOfElement(inStoreBtn);
        forceClickElement(inStoreBtn);
    }

    public void openProfilePage() {
        waitVisibilityOfElement(profileDropdown);
        forceClickElement(profileDropdown);
        forceClickElement(profileBtn);
    }

    public String clickOnlineCashback(String categorySelected) {
        waitVisibilityOfElement(onlineCashbackBtn);
        clickButton(onlineCashbackBtn);
        for (WebElement category : onlineCashbackList) {
            if (getText(category).equals(categorySelected)) {
                waitVisibilityOfElement(category);
                clickButton(category);
                break;
            }
        }
        waitForPageToLoad();
        return categorySelected;
    }

    public void clickWallet() {
        waitVisibilityOfElement(profileDropdown);
        clickButton(walletBtn);
        waitVisibilityOfElement(transactionSection);
    }

    public void clickLogOut() {
        clickButton(profileDropdown);
        waitVisibilityOfElement(logOutBtn);
        clickButton(logOutBtn);
        checkIfPopUpExist();
    }

    public void waitUntilGoogleMailAuthenticated() {
        while (!profileDropdown.isDisplayed()) {
            driverWait(60);
            refreshCurrentPage();
        }
        checkIfPopUpExist();
    }

    Helper helper = new Helper();

    public void checkThatHomePageOpened() {
        waitVisibilityOfElement(luckyBrandLogo);
        Assert.assertTrue(luckyBrandLogo.isDisplayed());
        Assert.assertTrue(headingText.isDisplayed());
    }

    public void addRegisteredNewsLetterEmail(String email) {
        helper.setPropertiesFileName("LoginData.properties");
        clearField(newsLetterEmailField);
        addText(newsLetterEmailField, helper.getValuesFromPropertiesFile(email));
    }

    public void addNewEmail() {
        String email = helper.generateRandomText(7) + "@mail.com";
        deleteTextInField(newsLetterEmailField);
        addText(newsLetterEmailField, email);
    }

    public void clickSubscribeBtn() {
        clickButton(subscribeBtn);
    }

    public void checkNewsLetterSuccessMessage(String successMsg) {
        Assert.assertEquals(successMessage.getText(), successMsg);
    }

    public void resetNewsLetterSubscription(String email, String language) {
        helper.setPropertiesFileName("LoginData.properties");
        DatabaseHelper.setUpDBConnection(language);
        DatabaseHelper.updateDatabaseValues("DELETE FROM AffiliateAnonymousSubscribedUsers WHERE Email = '" +
                helper.getValuesFromPropertiesFile(email) + "'");
        DatabaseHelper.closeDBConnection();
    }

    public void checkIfPopUpExist() {
        if (popupCloseBtn.size() > 0) {
            clickButton(popupCloseBtn.get(0));
            driverWait(30);
        }
    }

    public void openPortalURL(String language) {
        helper.setPropertiesFileName("PortalURLs.properties");
        switch (language) {
            case "Arabic_Egypt":
                driver.navigate().to(helper.getValuesFromPropertiesFile("EgyptURL"));
                refreshCurrentPage();
                checkIfPopUpExist();
                waitForPageToLoad();
                break;
            case "English":
                driver.navigate().to(helper.getValuesFromPropertiesFile("EgyptURL"));
                refreshCurrentPage();
                checkIfPopUpExist();
                clickButton(languageBtn);
                checkIfPopUpExist();
                waitForPageToLoad();
                break;
            case "Arabic_Morocco":
                driver.navigate().to(helper.getValuesFromPropertiesFile("MoroccoURL"));
                clickButton(languageBtn);
                waitForPageToLoad();
                break;
            case "French":
                driver.navigate().to(helper.getValuesFromPropertiesFile("MoroccoURL"));
                waitForPageToLoad();
                break;
        }
    }
}
