package com.lucky.qa.connectors;

import com.lucky.qa.utilities.Helper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.util.concurrent.TimeUnit;


public class Hook extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    private static String baseURL = "https://wcb.staging.thelucky.io/Home";



    public static WebDriver getDriver() {
        return driver;
    }


    public static void setDriver(WebDriver driver) {
        Hook.driver = driver;
    }



@Before ()
    public static WebDriver startDriver() {
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/Driver/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.navigate().to(baseURL);

    return driver;
    }

    //method to take screenshot in case scenario failed

    @AfterMethod
    public void screenShotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed");
            System.out.println("Taking Screenshot....");
            Helper.captureScreenShot(driver, result.getName());
        }
    }


    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
