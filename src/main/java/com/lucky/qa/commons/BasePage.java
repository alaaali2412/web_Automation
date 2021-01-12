package com.lucky.qa.commons;


import com.lucky.qa.pages.PageGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends PageGenerator {

    protected WebDriverWait wait;
    public Actions actions;



    public BasePage(WebDriver driver)
    {
        super(driver);

        PageFactory.initElements(driver, this);
    }


    protected void clickButton(WebElement element) {
        element.click();
    }

    protected void addText(WebElement element, String word) {
        element.sendKeys(word);
    }
    protected static void clearField(WebElement button) {
        button.clear();
    }


    protected String getText(WebElement element) {
        element.getText();
        return null;
    }

    protected boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
    protected void waitVisibilityOfElement (int seconds, WebElement element){
        wait = new WebDriverWait(driver,seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
}

public void waitUntilPageLoaded(int seconds){
    wait = new WebDriverWait(driver, 30);
    wait.until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver wdriver) {
            return ((JavascriptExecutor) driver).executeScript(
                    "return document.readyState"
            ).equals("complete");
        }
    });
}
}
