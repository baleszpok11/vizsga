package com.example.vizsga;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.LocaleList;
import androidx.appcompat.app.AppCompatDelegate;
import java.util.Locale;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Apply saved language settings when the application starts
        applySavedLanguage();
    }

    private void applySavedLanguage() {
        SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String language = prefs.getString("language", "en"); // Default to English

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            config.setLocales(new LocaleList(locale));
        }

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Optionally set AppCompatDelegate to avoid dark mode issues
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}