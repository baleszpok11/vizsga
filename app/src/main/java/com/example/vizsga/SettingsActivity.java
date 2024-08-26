package com.example.vizsga;// SettingsActivity.java
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup languageRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        languageRadioGroup = findViewById(R.id.radio_group_language);

        // Load saved language preference
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

            // Update the app language
            Locale locale = new Locale(selectedLanguage);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());

            // Restart the activity to apply changes
            recreate();
        });
    }
}