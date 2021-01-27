package com.lucky.qa.commons;


import com.lucky.qa.pages.PageGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage extends PageGenerator {

    protected WebDriverWait wait;
    public Actions actions;


    public BasePage(WebDriver driver) {
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

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void waitVisibilityOfElement(int seconds, WebElement element) {
        wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitForElements(int TimeOut) {

        driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
    }

    public void scrollToEndOfScreen() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
