package com.lucky.qa.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public String generateRandomText(int length) {
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
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String character = "!@#$%^&*";
        return RandomStringUtils.random(length, uppercase + character + numbers);
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

    static Connection connection = null;

    public static void setUpDBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://luckydbsrv.database.windows.net:1433;database=LuckyStaging;",
                    "Azure_WriteLogin", "AzP@ss!ucky2");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String getValueFromDatabase(String query) {
        String res = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next())
                res = result.getString(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public static String updateDatabaseValues(String query) {
        String res = null;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public static void closeDBConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
