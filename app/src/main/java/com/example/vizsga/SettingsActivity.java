package com.example.vizsga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup languageRadioGroup = findViewById(R.id.radio_group_language);

        SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String language = prefs.getString("language", "en");

        if ("hu".equals(language)) {
            languageRadioGroup.check(R.id.radio_button_hungarian);
        } else {
            languageRadioGroup.check(R.id.radio_button_english);
        }

        languageRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String selectedLanguage = "en";
            if (checkedId == R.id.radio_button_hungarian) {
                selectedLanguage = "hu";
            }

            // Save the selected language preference
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("language", selectedLanguage);
            editor.apply();

            updateAppLanguage(selectedLanguage);

            Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateAppLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        config.setLocales(new LocaleList(locale));

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}