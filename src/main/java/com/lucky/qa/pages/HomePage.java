package com.lucky.qa.pages;

import com.lucky.qa.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.className;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage goToThePage() {
        driver.get("https://wcb.staging.thelucky.io/Home");
        return this;
    }

    public void dropDownLanguage() {
        driver.findElement(className("dropdown-toggle")).click();
    }

    public void selectItemLanguage() {
        driver.findElement(className("dropdown-item")).click();
    }


}