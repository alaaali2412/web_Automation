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

    //Method to generate random Number.
    public String generateRandomNumber(int length) {
        char[] chars = "0123456789".toCharArray();
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
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];
        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));
        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return String.valueOf(password);
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

    public String generateRandomArabicText(int length) {
        String[] letters = {"أ", "ب", "ت", "ث", "ج", "ح", "خ", "د", "ذ", "ر", "ز",
                "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "م", "ن", "ه", "و", "ي"};
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String c = letters[random.nextInt(letters.length)];
            sb.append(c);
        }
        return sb.toString();
    }

}
