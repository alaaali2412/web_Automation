package com.lucky.qa.pages;


import com.lucky.qa.common.BasePage;
import com.lucky.qa.connectors.DriverFactory;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


public class PageGenerator {
    public static <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
        try {
            return PageFactory.initElements(DriverFactory.getDriver(), pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
