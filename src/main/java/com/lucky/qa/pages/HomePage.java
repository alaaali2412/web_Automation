package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@class ='px-xl-3 px-lg-2 nav-link-item'][4]")
    private WebElement signInBtn;

    @FindBy(xpath = "//button/*[text()='Facebook']")
    private WebElement facebookBtn;

    @FindBy(xpath = "//button/*[text()='Google']")
    private WebElement googleBtn;

    @FindBy(xpath = "//*[@class = 'box-body']/a")
    private WebElement emailBtn;

    @FindBy(xpath = "//div[1]/nav/div/div[2]")
    private WebElement inStoreBtn;

    @FindBy(xpath = "//nav/div//div[7]/div/a")
    private WebElement profileDropdown;

    @FindBy(xpath = "//div[7]/div/div/a[1]")
    private WebElement profileBtn;

    @FindBy(xpath = "//div[7]/div/div/a[2]")
    private WebElement logOutBtn;

    @FindBy(id = "exampleForm.ControlInput1")
    private WebElement loggedInEmail;

    @FindBy(xpath = "//*[@id='basic-nav-dropdown'][text()='Online Cashbacks']")
    private WebElement onlineCashbackBtn;

    @FindBy(xpath = "//nav/div/div[1]/div/div/a")
    private List<WebElement> onlineCashbackList;

    @FindBy(className = "//header/div[1]/nav//div/span")
    private WebElement logoBtn;

    @FindBy(xpath = "//*[@class = 'container-md']//div[3]")
    private WebElement walletBtn;

    @FindBy(className = "wallet-body")
    private WebElement transactionSection;

    @FindBy(className = "hero-banner")
    private WebElement homeBanner;

    @FindBy(xpath = "//*[@class = 'container-md']/a")
    private WebElement homeScreenBtn;

    @FindBy(xpath = "//*/ul/li[2]/a")
    private WebElement contactUsBtn;

    public void clickContactUsBtn() {
        clickButton(contactUsBtn);
    }

    public void openHomeScreen() {
        clickButton(homeScreenBtn);
    }

    public void clickSignInBtn() {
        waitVisibilityOfElement(signInBtn);
        clickButton(signInBtn);
    }

    public void facebookLogin() {
        clickButton(facebookBtn);
    }

    public void googleLogin() {
        waitVisibilityOfElement(googleBtn);
        clickButton(googleBtn);
    }

    public void clickSignupBtn() {
        waitVisibilityOfElement(emailBtn);
        clickButton(emailBtn);
    }

    public void clkInStoreBtn() {
        clickButton(inStoreBtn);
    }

    public void openProfilePage() {
        waitVisibilityOfElement(profileDropdown);
        clickButton(profileDropdown);
        clickButton(profileBtn);
    }

    public String clickOnlineCashback(String categorySelected) {
        waitVisibilityOfElement(onlineCashbackBtn);
        clickButton(onlineCashbackBtn);
        for (WebElement category : onlineCashbackList) {
            if (getText(category).equals(categorySelected)) {
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
        clickButton(logOutBtn);
    }
}
