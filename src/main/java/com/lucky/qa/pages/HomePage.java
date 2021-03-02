package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div/header/div[1]/nav/div/div[7]/a")
    private WebElement signInBtn;

    @FindBy(xpath = "//button/*[text()='Facebook']")
    private WebElement FacebookBtn;

    @FindBy(xpath = "//button/*[text()='Google']")
    private WebElement GoogleBtn;

    @FindBy(xpath = "//div[1]/nav/div/div[2]")
    private WebElement inStoreBtn;

    @FindBy(xpath = "//nav/div//div[7]/div/a")
    private WebElement profileDropdown;

    @FindBy(xpath = "//div[7]/div/div/a[1]")
    private WebElement profileBtn;

    @FindBy(id = "exampleForm.ControlInput1")
    private WebElement loggedInEmail;

    @FindBy(xpath = "//*[@id='basic-nav-dropdown'][text()='Online Cashbacks']")
    private WebElement onlineCashbackBtn;

    @FindBy(xpath = "//nav/div/div[1]/div/div/a")
    private List<WebElement> onlineCashbackList;

    @FindBy(className = "//header/div[1]/nav//div/span")
    private WebElement logoBtn;

    @FindBy(xpath = "//nav/div/div[3]/a")
    private WebElement walletBtn;

    @FindBy(className = "wallet-body")
    private WebElement transactionSection;

    @FindBy(className = "hero-banner")
    private WebElement homeBanner;

    public void clickSignInBtn() {
        waitVisibilityOfElement(signInBtn);
        clickButton(signInBtn);

    }


    public void facebookLogin() {
        clickButton(FacebookBtn);
    }

    public void clkInStoreBtn() {
        // waitVisibilityOfElement(inStoreBtn);
        clickButton(inStoreBtn);
    }

    public void googleLogin() {
        waitVisibilityOfElement(GoogleBtn);
        clickButton(GoogleBtn);
    }

    public void openProfilePage() {
        //waitVisibilityOfElement(profileDropdown);
        clickButton(profileDropdown);
        clickButton(profileBtn);
    }

    public String clickOnlineCashback(String categorySelected) {
        clickButton(onlineCashbackBtn);
        for (WebElement category : onlineCashbackList) {
            if (getText(category).equals(categorySelected)) {
                clickButton(category);
                break;
            }
        }
        return categorySelected;
    }

    public void clickWallet() {
        clickButton(walletBtn);
        waitVisibilityOfElement(transactionSection);
    }


}
