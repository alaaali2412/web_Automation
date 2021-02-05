package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

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

    @FindBy(id = "formEmail")
    private WebElement inputEmail;

    @FindBy(id = "formPassword")
    private WebElement inputPassword;

    @FindBy(xpath = "//div[3]/button")
    private WebElement loginBtn;

    @FindBy(id = "identifierId")
    private WebElement googleEmail;

    @FindBy(id = "password")
    private WebElement googlePassword;

    @FindBy(id = "identifierNext")
    private WebElement nextGoogleBtn;

    @FindBy(className = "modal-open")
    private WebElement modal;

    @FindBy(id = "passwordNext")
    private WebElement nextGoogleBtn2;

    @FindBy(className = "carousel-inner")
    private WebElement homeBanner;

    @FindBy(xpath = "//div/header/div[1]/nav/div/div[7]/a")
    private WebElement signInBtn;


    public String login(String email, String password) {
        waitVisibilityOfElement(inputPassword);
        clearField(inputEmail);
        addText(inputEmail, email);
        clearField(inputPassword);
        addText(inputPassword, password);
        clickButton(loginBtn);
        waitVisibilityOfElement(homeBanner);
        driver.navigate().refresh();
        return email;
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
        driver.switchTo().window(parentWindow);
        waitVisibilityOfElement(signInBtn);
    }

    public String loginGoogle(String email, String password) throws InterruptedException {
        actions = new Actions(driver);
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindow = driver.getWindowHandles();
        for (String child : allWindow) {
            if (!parentWindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                actions.moveToElement(googleEmail).sendKeys(email).build().perform();
                clickButton(nextGoogleBtn);
                waitVisibilityOfElement(googlePassword);
                actions.moveToElement(googlePassword).sendKeys(password).build().perform();
                clickButton(nextGoogleBtn2);
            }
        }
        driver.switchTo().window(parentWindow);
        Thread.sleep(5000);
        driver.navigate().refresh();
        return email;

    }


}
