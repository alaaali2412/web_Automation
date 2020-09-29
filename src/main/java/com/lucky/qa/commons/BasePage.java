package com.lucky.qa.commons;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public void click(WebElement element) {
        element.click();
    }

    public void writeText(WebElement element, String word) {
        element.sendKeys(word);
    }

    public String getText(WebElement element) {
        element.getText();
        return null;
    }

}
