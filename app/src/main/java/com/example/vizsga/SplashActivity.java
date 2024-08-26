package com.example.vizsga;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Find the root view or the specific view you want to animate
        View splashContent = findViewById(R.id.splash_content);

        // Load the animation
        Animation slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_left);

        // Apply the animation to the view
        splashContent.startAnimation(slideInAnimation);

        // Start the next activity after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}