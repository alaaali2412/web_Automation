package com.lucky.qa.pages;

import com.lucky.qa.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.Set;

import static org.openqa.selenium.By.className;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "btn-icon")
    private List<WebElement> listItems;

    @FindBy(className = "btn-inner--text")
    private WebElement facebookButton;

    @FindBy(id = "email")
    private WebElement  inputEmailFb;

    @FindBy(id = "pass")
    private WebElement  inputPassWordFb;

    @FindBy(xpath = "//*[@id=\"u_0_0\"]")
    private WebElement  loginBtnFb;

    @FindBy(xpath = "//*[@id='buttons']")
    private WebElement  continueBtnFb;

    @FindBy(id = "formEmail")
    private WebElement  inputEmail;

    @FindBy(id = "formPassword")
    private WebElement  inputPassword;

    @FindBy(xpath = "//div[3]/button")
    private WebElement loginBtn;

    @FindBy(id = "identifierId")
    private WebElement googleEmail;

    @FindBy(id = "password")
    private WebElement googlePassword;

    @FindBy(id = "identifierNext")
    private WebElement nextGoogleBtn;

    @FindBy(className = "modal-open")
    private WebElement  modal;

    @FindBy(id = "passwordNext")
    private WebElement nextGoogleBtn2;
    public void clickOnGoogleLogin(){
     listItems.get(0).click();
    }

    public void clickOnFacebookLogin(){
        listItems.get(1).click();
    }



    public void closePopUpRegister() {
        waitToAppearPopUp();
        driver.findElement(className("close")).click();
    }

    public String login(String email, String password) throws InterruptedException {
        waitVisibilityOfElement(20, loginBtn);
        clearField(inputEmail);
        addText(inputEmail, email);
        clearField(inputPassword);
        addText(inputPassword, password);
        waitForElements(10);
        clickButton(loginBtn);
        Thread.sleep(2000);
        return email;
    }


    public void loginFB(String email, String password) throws InterruptedException {

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindow = driver.getWindowHandles();
        for(String child:allWindow)
        {
            if(!parentWindow.equalsIgnoreCase(child))
            {
                driver.switchTo().window(child);
                clearField(inputEmailFb);
                addText(inputEmailFb, email);
                clearField(inputPassWordFb);
                addText(inputPassWordFb, password);
                clickButton(loginBtnFb);
                Thread.sleep(3000);
            }
        }
        driver.switchTo().window(parentWindow);


    }

    public String loginGoogle(String email, String password) throws InterruptedException {
        actions = new Actions(driver);
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindow = driver.getWindowHandles();
        for(String child : allWindow)
        {
            if(!parentWindow.equalsIgnoreCase(child))
            {
                driver.switchTo().window(child);
                actions.moveToElement(googleEmail).sendKeys(email).build().perform();
                clickButton(nextGoogleBtn);
                waitVisibilityOfElement(20,googlePassword);
                actions.moveToElement(googlePassword).sendKeys(password).build().perform();
                clickButton(nextGoogleBtn2);
                Thread.sleep(9000);
            }
        }
        driver.switchTo().window(parentWindow);
        return email;

    }

    public void waitToAppearPopUp() {
        WebElement element = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(modal));
    }

    public void ClickLoginBtn() throws InterruptedException {
        clickButton(loginBtn);
        Thread.sleep(3000);
}

}
