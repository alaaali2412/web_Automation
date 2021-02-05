package com.lucky.qa.pages;


import com.lucky.qa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageGenerator {

    public static WebDriver driver;
    //Constructor
    public PageGenerator(WebDriver driver){
        PageGenerator.driver = driver;

    }

    public static <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass,WebDriver driver) {
        try {
            return PageFactory.initElements(driver, pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }





}
