package com.lucky.qa;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class test {
    public String language(String language) {
        String localLanguage;
        switch (language) {
            case "Arabic_Egypt":
                localLanguage = "ar_EG";
                break;
            case "English":
                localLanguage = "en_EG";
                break;
            case "Arabic_Morocco":
                localLanguage = "ar_MA";
                break;
            case "French":
                localLanguage = "fr_MA";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
        return localLanguage;
    }

    private ResourceBundle fromClassLoader(String language) {
        Locale locale = new Locale(language(language));
        ClassLoader loader = null;
        try {
            File file = new File(System.getProperty("user.dir") + "src/test/resources/");
            URL[] urls = {file.toURI().toURL()};
            loader = new URLClassLoader(urls);
            loader.getResource("LanguageTest");
        } catch (Exception e) {
        }
        return ResourceBundle.getBundle("LanguageTest", locale, loader);
    }

    public String detectLanguage(String language, String message) {
    /*    Locale locale = new Locale(language(language));
        ResourceBundle resource = PropertyResourceBundle.getBundle("LanguageTest", locale);*/
        return fromClassLoader(language).getString(message);
    }

}
