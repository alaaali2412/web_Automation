package com.lucky.qa.utilities;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LanguageReader {
    private static ResourceBundle resource = null;
    private static Locale locale = null;
    Helper helper = new Helper();

    public String detectLanguage(String message) {
        helper.setPropertiesFileName("Languague.properties");
        String language = helper.getValuesFromPropertiesFile("local.language");
        locale = new Locale(language);
        resource = PropertyResourceBundle.getBundle("LanguageTest", locale);

        return resource.getString(message);

    }
}
