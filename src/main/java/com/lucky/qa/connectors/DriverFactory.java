package com.lucky.qa.connectors;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverFactory {
    // to create separate driver for each chrome session
    static private final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    DriverFactory() {
    }

    // To quit the drivers and browsers at the end only.
    public static List<WebDriver> storedDrivers = new ArrayList<>();

    // to get the driver and used for any other class
    public static synchronized WebDriver getDriver() {
        return drivers.get();
    }

    //to add all the created drivers is list and quit them at once at the end of the session
    public static void addDriver(WebDriver driver) {
        storedDrivers.add(driver);
        drivers.set(driver);
    }

    // To remove the driver from the storedDrivers list
    public static void removeDriver() {
        storedDrivers.remove(drivers.get());
        drivers.remove();
    }
}
