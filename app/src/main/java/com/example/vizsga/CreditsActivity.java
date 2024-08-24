package com.example.vizsga;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        WebView webView = findViewById(R.id.webview_credits);
        webView.loadUrl("file:///android_asset/credits.html");
    }
}
