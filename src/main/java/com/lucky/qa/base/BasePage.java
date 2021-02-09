package com.lucky.qa.base;


import com.lucky.qa.pages.PageGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage extends PageGenerator {

    protected Actions actions;


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
        return element.getText();
    }


    public void scrollToEndOfScreen() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    protected void waitVisibilityOfElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOf(element));

    }



/*
    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

 */

}
