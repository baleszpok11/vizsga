package com.example.vizsga;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox likedCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        likedCheckBox = findViewById(R.id.checkbox_liked);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        likedCheckBox.setChecked(prefs.getBoolean("liked", false));

        likedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("liked", isChecked);
            editor.apply();
        });
    }

}
