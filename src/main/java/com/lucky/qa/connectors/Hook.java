package com.lucky.qa.connectors;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;


public class Hook {


        private static WebDriver driver;

        public static WebDriver getDriver() {

            return driver;
        }

        public static void setDriver(WebDriver driver) {
            Hook.driver = driver;
        }

        @Before
        public static void setupClass() {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

//        @After
//        public static void teardown() {
//            if (driver != null) {
//                driver.quit();
//            }
//        }

    }

