package com.lucky.qa.common;


import com.lucky.qa.connectors.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public WebDriver driver;
    protected Actions actions;

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

    public void scrollToViewElement(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void forceClickElement(WebElement element) {
        actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).click(element).build().perform();
    }

    public void forceAddText(WebElement element, String value) {
        actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).click(element).sendKeys(value).build().perform();
    }

    public void openNewTab() {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.open()");
    }

    public void moveToTab(int tab) {
        ArrayList<String> tabs = new ArrayList<>(DriverFactory.getDriver().getWindowHandles());
        DriverFactory.getDriver().switchTo().window(tabs.get(tab));
    }

    public void clearDefaultValueOfCopy(WebElement element) {
        addText(element, " ");
        element.sendKeys(Keys.COMMAND + "a");
        element.sendKeys(Keys.COMMAND + "c");
    }

    public void pasteTextInField(WebElement element) {
        element.sendKeys(Keys.SHIFT, Keys.INSERT);
    }

    public void deleteTextInField(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].value = '';", element);
    }

    public void confirmAction(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }


    public void refreshCurrentPage() {
        DriverFactory.getDriver().navigate().refresh();
    }

    public void driverWait(int timeInSeconds) {
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
    }

    public void waitForTextToBeVisible(WebElement element) {
        FluentWait<String> wait = new FluentWait<>(element.getText())
                .pollingEvery(Duration.ofSeconds(5))
                .withTimeout(Duration.ofSeconds(60))
                .ignoring(Exception.class);
        wait.until(t -> element.getText().length() != 0);
    }

    public void waitForTextInAttributeToBeExist(String value) {
        FluentWait<String> wait = new FluentWait<>(value)
                .pollingEvery(Duration.ofSeconds(5))
                .withTimeout(Duration.ofSeconds(60))
                .ignoring(Exception.class);
        wait.until(t -> value.length() != 0);
    }

    public void waitUntilTextEqual(String value, WebElement element) {
        FluentWait<String> wait = new FluentWait<>(value)
                .pollingEvery(Duration.ofSeconds(5))
                .withTimeout(Duration.ofSeconds(120))
                .ignoring(Exception.class);
        wait.until(t -> element.getText().contains(value));
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 60);
        wait.until((ExpectedCondition<Boolean>) webDriver -> ((JavascriptExecutor) DriverFactory.getDriver()).
                executeScript("return document.readyState").equals("complete"));
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

    public String [] currentPortalLanguage(String language) {
        String localLanguage = null;
        String country = null;
        String [] locales = {localLanguage, country};
        switch (language) {
            case "Arabic_Egypt":
                locales[0] = "ar";
                locales[1] = "EG";
                break;
            case "English":
                locales[0] = "en";
                locales[1] = "EG";
                break;
            case "Arabic_Morocco":
                locales[0] = "ar";
                locales[1] = "MA";
                break;
            case "French":
                locales[0] = "fr";
                locales[1] = "MA";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }

        return locales ;
    }

    public String detectLanguage(String language, String message) {
        Locale locale = new Locale(currentPortalLanguage(language)[0], currentPortalLanguage(language)[1]);
        ClassLoader loader = null;
        try {
            File file = new File(System.getProperty("user.dir") + "src/main/resources/");
            URL[] urls = {file.toURI().toURL()};
            loader = new URLClassLoader(urls);
        } catch (Exception e) {
        }
      return   ResourceBundle.getBundle("LanguageTest",locale , loader).getString(message);
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void MoveTOPreviousPage() {
        DriverFactory.getDriver().navigate().back();
    }

}
