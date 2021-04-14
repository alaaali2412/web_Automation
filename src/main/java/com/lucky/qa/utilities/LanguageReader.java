package com.lucky.qa.utilities;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LanguageReader {
    private static ResourceBundle resource = null;
    private static Locale locale = null;
    private static final Helper helper = new Helper();


    public static String language() {
        helper.setPropertiesFileName("Languague.properties");
        return helper.getValuesFromPropertiesFile("local.language");
    }

    public static String detectLanguage(String message) {
        locale = new Locale(language());
        resource = PropertyResourceBundle.getBundle("LanguageTest", locale);

        return resource.getString(message);

    }
}
