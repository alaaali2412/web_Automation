package com.lucky.qa.connectors;

import com.lucky.qa.utilities.Helper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Hook {
    Helper helper = new Helper();
    private WebDriver driver;

    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.managed_default_content_settings.ads", 1);
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }

    @Before
    public void startDriver() {
        helper.setPropertiesFileName("Languague.properties");
        if (DriverFactory.getDriver() == null) {
            //WebDriverManager download the chrome driver and run it,
            // no need to download the driver and set path for it
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOption());
            driver.manage().window().maximize();
            DriverFactory.addDriver(driver);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.navigate().to(helper.getValuesFromPropertiesFile("PortalUrl"));

        }
    }

    @After
    public void tearsDown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                DriverFactory.removeDriver();
                DriverFactory.storedDrivers.forEach(WebDriver::quit);

            }
        });
    }
}
