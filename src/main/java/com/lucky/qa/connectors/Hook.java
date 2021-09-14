package com.lucky.qa.connectors;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Hook {
    static Connection connection = null;
    private WebDriver driver;

    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.managed_default_content_settings.ads", 1);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--headless");
        return options;
    }

    @Before()
    public void startDriver() {
        if (DriverFactory.getDriver() == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOption());
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            DriverFactory.addDriver(driver);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        }
    }

    @After(order = 1)
    public void tearsDown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                DriverFactory.storedDrivers.forEach(WebDriver::quit);
                DriverFactory.removeDriver();
            }
        });
    }

    @After(order = 0)
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
    }
}

