package com.lucky.qa.utilities;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public char generateRandomCharacters() {
        int random = (int) (Math.random() * 62);
        if (random <= 9) {
            int number = random + 48;
            return (char) (number);
        } else if (random <= 35) {
            int uppercase = random + 55;
            return (char) (uppercase);
        } else {
            int lowercase = random + 61;
            return (char) (lowercase);
        }
    }

    public String generateRandomPassword(int length) {
        StringBuilder randomPassword = new StringBuilder();
        for (int j = 0; j < length; j++) {
            randomPassword.append(generateRandomCharacters());
        }
        return randomPassword.toString();
    }


    public String generateRandomArabicText(int length) {
        String[] letters = {"أ", "ب", "ت", "ث", "ج", "ح", "خ", "د", "ذ", "ر", "ز",
                "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "م", "ن", "ه", "و", "ي"};
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String characters = letters[random.nextInt(letters.length)];
            sb.append(characters);
        }
        return sb.toString();
    }

}
