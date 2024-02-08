package com.rybina.http.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {

    public static void main(String[] args) {
        Locale locale = new Locale("uk", "UA");
        System.out.println(Locale.US);
        System.out.println(Locale.getDefault());

        ResourceBundle translations = ResourceBundle.getBundle("translations");
        System.out.println(translations.getString("page.login.password"));
    }
}
