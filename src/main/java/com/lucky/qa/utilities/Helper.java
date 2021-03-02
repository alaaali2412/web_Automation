package com.lucky.qa.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

public class Helper {

    protected String propertiesFileName;

    public String getPropertiesFileName() {
        return propertiesFileName;
    }

    public void setPropertiesFileName(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    public String getValuesFromPropertiesFile(String value) {
        Properties properties = new Properties();
        FileInputStream file;
        try {
            file = new FileInputStream(System.getProperty("user.dir") + "/propFiles/" + getPropertiesFileName());
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not read properties from file: [" + getPropertiesFileName() + "].", e);
        }
        return properties.getProperty(value);
    }

    //Method to generate random Name.
    public String generateRandomName(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();

    }

    public String updateValueInPropertiesFile(String key, String value) {
        Properties properties = new Properties();
        FileInputStream fileIn;
        FileOutputStream fileOut;
        try {
            fileIn = new FileInputStream(System.getProperty("user.dir") + "/propFiles/" + getPropertiesFileName());
            properties.load(fileIn);
            fileOut = new FileOutputStream(System.getProperty("user.dir") + "/propFiles/" + getPropertiesFileName());
            properties.setProperty(key, value);
            properties.store(fileOut, "updated successfully");
        } catch (IOException e) {

            System.out.println("Exception while taking screenshot" + e.getMessage());
            throw new RuntimeException("Could not read properties from file: [" + getPropertiesFileName() + "].", e);

        }
        return properties.getProperty(value);
    }

    //Method to generate random Number.
    public static String generateRandomPassword(int length) {
        char[] chars = "0123456789&@abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();

    }

    //Method to take screenshot the test case fail
    public static void captureScreenShot(WebDriver driver, String screenshotname) {
        Path dest = Paths.get("./Screenshots", screenshotname + ".png");
        try {
            Files.createDirectories(dest.getParent());
            FileOutputStream out = new FileOutputStream(dest.toString());
            out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            out.close();

        } catch (IOException e) {
            System.out.println("Exception while taking screenshot" + e.getMessage());
        }
    }


}
