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
        applySavedLanguage();
    }

    private void applySavedLanguage() {
        SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String language = prefs.getString("language", "en");

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        config.setLocales(new LocaleList(locale));

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}