package com.lucky.qa.connectors;


import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Hook {
    private WebDriver driver;

    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.managed_default_content_settings.ads", 1);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("−−lang=en");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--whitelisted-ips");
        options.addArguments("--disable-gpu ");
        options.addArguments("--user-agent='Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/93.0.4577.63 Safari/537.36'");

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
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    @After(order = 1)
    public void tearsDown(Scenario scenario) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DriverFactory.storedDrivers.forEach(WebDriver::quit);
            DriverFactory.removeDriver();
        }));
    }

    @AfterStep(order = 0)
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);

                scenario.attach(screenshot, "image/png", scenario.getName() + scenario.getId());
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
    }
}

