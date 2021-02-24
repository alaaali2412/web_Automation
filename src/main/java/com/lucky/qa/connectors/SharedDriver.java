package com.lucky.qa.connectors;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SharedDriver {
    private static String baseURL = "https://wcb.staging.thelucky.io/Home";

    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.managed_default_content_settings.ads", 1);
        options.setExperimentalOption("prefs", chromePrefs);

        return options;
    }

    public SharedDriver() {
        if (DriverFactory.getDriver() == null) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver(chromeOption());
            driver.manage().window().maximize();
            DriverFactory.addDriver(driver);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.navigate().to(baseURL);

        }
    }
}
