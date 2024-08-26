package com.example.vizsga;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private ProblemAdapter problemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ImageButton navButton = findViewById(R.id.nav_button);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JSONUtils.loadProblemsFromUrl(problems -> {
            problemAdapter = new ProblemAdapter(problems, problem -> {
                Intent intent = new Intent(MainActivity.this, ProblemDetailActivity.class);
                intent.putExtra("problem", problem);
                startActivity(intent);
            });
            recyclerView.setAdapter(problemAdapter);

            Animation slideInAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_from_top);
            recyclerView.startAnimation(slideInAnimation);
        });

        navButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_settings) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            } else if (id == R.id.nav_credits) {
                startActivity(new Intent(MainActivity.this, CreditsActivity.class));
            } else {
                return false;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        setMenuTitles();
    }

    private void setMenuTitles() {
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_settings).setTitle(R.string.settings);
        menu.findItem(R.id.nav_credits).setTitle(R.string.credits);
    }
}