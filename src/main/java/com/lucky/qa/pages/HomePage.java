package com.lucky.qa.pages;

import com.lucky.qa.commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/div[4]/div/div/div")
    private WebElement popUp;

    @FindBy(className = "react-toast-notifications")
    private WebElement toastSuccesMessage;

    @FindBy(xpath = "//div[6]/div/div/div/section[1]/button")
    private WebElement closeBtn;

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
    private List<WebElement> onlineCashbackList;


    public void dropDownLanguage() {
        driver.findElement(className("dropdown-toggle")).click();
    }

    public void selectItemLanguage() {
        driver.findElement(className("dropdown-item")).click();
    }

    public void appearSuccess() {
        Assert.assertTrue(toastSuccesMessage.isDisplayed());
        System.out.println("Toast message success");
    }



    public boolean checkIfPortalOPens() {
        if (driver.findElement(xpath("/html/body/div[4]/div/div/div")).isDisplayed()){
            return true;
        }
       else return false ;

    }

    public void closePopUp()  {
        clickButton(closeBtn);

    }
    public void clickSignInBtn() throws InterruptedException {
        clickButton(signInBtn);
        Thread.sleep(5000);
    }

    public boolean checkIfUserLggedIn(){
      Boolean login = isElementPresent(By.xpath ("//div/header/div[1]/nav/div/div[7]/a")  );
      return login;
    }


    public void facebookLogin()  {
        clickButton(FacebookBtn);
    }

    public void clkInStoreBtn()   {
        waitVisibilityOfElement(20, inStoreBtn);
        clickButton(inStoreBtn);
    }

    public void googleLogin()   {
        clickButton(GoogleBtn);
    }

    public String checkUserLogin(){
        waitUntilPageLoaded(30);
        clickButton(profileDropdown);
        clickButton(profileBtn);
        return loggedInEmail.getAttribute("value");

    }

    public void clickOnlinCashback(){
       for ( WebElement category : onlineCashbackList){
           System.out.println(category.getText());
       }

    }
}