package com.lucky.qa.common;


import com.lucky.qa.connectors.DriverFactory;
import com.lucky.qa.utilities.Helper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class BasePage {
    public WebDriver driver;
    protected Actions actions;
    private static ResourceBundle resource = null;
    private static Locale locale = null;
    private final Helper helper = new Helper();

    public BasePage(WebDriver driver) {
        this.driver = driver;
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
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTopOfScreen() {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.scrollTo(0,document.body.scrollTop)");
    }

    public void forceClickElement(WebElement element) {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).click(element).build().perform();
    }

    public void forceAddText(WebElement element, String value) {
        actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).click(element).sendKeys(value).build().perform();
    }

    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }

    public void moveToTab(int tab) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab));
    }

    public void clearDefaultValueOfCopy(WebElement element) {
        addText(element, " ");
        element.sendKeys(Keys.COMMAND + "a");
        element.sendKeys(Keys.COMMAND + "c");
    }

    public void pasteTextInField(WebElement element) {
        element.sendKeys(Keys.COMMAND + "v");
    }

    public void deleteTextInField(WebElement element) {
        element.sendKeys(Keys.COMMAND + "a");
        element.sendKeys(Keys.DELETE);
    }

    public void confirmAction(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }


    public void refreshCurrentPage() {
        DriverFactory.getDriver().navigate().refresh();
    }

    public void waitForTextToBeVisible(WebElement element) {
        FluentWait<String> wait = new FluentWait<>(element.getText())
                .pollingEvery(Duration.ofSeconds(5))
                .withTimeout(Duration.ofSeconds(30))
                .ignoring(Exception.class);
        wait.until(t -> element.getText().length() != 0);
    }

    public void waitForTextInAttributeToBeExist(String value) {
        FluentWait<String> wait = new FluentWait<>(value)
                .pollingEvery(Duration.ofSeconds(5))
                .withTimeout(Duration.ofSeconds(30))
                .ignoring(Exception.class);
        wait.until(t -> value.length() != 0);
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
        wait.until(webDriver -> ((JavascriptExecutor) DriverFactory.getDriver())).
                executeScript("return document.readyState").toString().equals("complete");
    }

    protected void waitVisibilityOfElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitVisibilityOfAllElements(List<WebElement> elements) {
        Wait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public String language(String language) {
        String localLanguage;
        switch (language) {
            case "Arabic_Egypt":
                localLanguage = "ar_EG";
                break;
            case "English":
                localLanguage = "en_EG";
                break;
            case "Arabic_Morocco":
                localLanguage = "ar_MA";
                break;
            case "French":
                localLanguage = "fr_MA";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
        return localLanguage;
    }

    public String detectLanguage(String language, String message) {
        locale = new Locale(language(language));
        resource = PropertyResourceBundle.getBundle("LanguageTest", locale);
        return resource.getString(message);
    }
}