package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "exampleForm.ControlInput1")
    private WebElement loggedInEmail;

    public String getLoggedInEmail(){
        return loggedInEmail.getAttribute("value");
    }
}
