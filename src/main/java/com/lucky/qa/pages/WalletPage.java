package com.lucky.qa.pages;

import com.lucky.qa.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WalletPage extends BasePage {
    public WalletPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "h2-text")
    private WebElement userbalance;

    public void getUserBalance(){
        System.out.println(userbalance.getText());

    }
}
