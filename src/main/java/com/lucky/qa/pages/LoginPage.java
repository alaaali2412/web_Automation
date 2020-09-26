package com.lucky.qa.pages;

import com.lucky.qa.commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FindBy(id = "formEmail")
    private WebElement  inputEmail;

    @FindBy(id = "formPassword")
    private WebElement  inputPassword;

    @FindBy(className = "py-3")
    private WebElement  buttonLogin;

    @FindBy(className = "modal-open")
    private WebElement  modal;


    public void clickOnGoogleLogin(){
     listItems.get(0).click();
    }

    public void clickOnFacebookLogin(){
        listItems.get(1).click();
    }

    public void setEmail(){
       writeText(inputEmailFb, "qa@thelucky.app");
       writeText(inputPassWordFb,"Qa1234567");
    }

    public void closePopUpRegister() {
        waitToAppearPopUp();
        driver.findElement(className("close")).click();
    }

    public void login() {
        WebElement element = (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.elementToBeClickable(inputEmail));
        writeText(inputEmail, "qa@thelucky.app");
        writeText(inputPassword, "Qa1234567&");
        click(buttonLogin);
    }

    public void waitToAppearPopUp() {
        WebElement element = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(modal));
    }



}
