package com.lucky.qa.pages;


import com.lucky.qa.base.BasePage;
import com.lucky.qa.connectors.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageGenerator {

    public WebDriver driver;
    //Constructor
    public PageGenerator(WebDriver driver){
        this.driver = driver;

    }

    public static <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
        try {
            return PageFactory.initElements(DriverFactory.getDriver(), pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }





}
