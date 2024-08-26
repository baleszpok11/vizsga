package com.example.vizsga;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton navButton;
    private RecyclerView recyclerView;
    private ProblemAdapter problemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navButton = findViewById(R.id.nav_button);
        recyclerView = findViewById(R.id.recycler_view); // Initialize RecyclerView

        // Set LayoutManager for RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load data and set up RecyclerView adapter
        JSONUtils.loadProblemsFromUrl(this, new JSONUtils.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(List<Problem> problems) {
                problemAdapter = new ProblemAdapter(problems, new ProblemAdapter.OnProblemClickListener() {
                    @Override
                    public void onProblemClick(Problem problem) {
                        // Handle problem click event
                        Intent intent = new Intent(MainActivity.this, ProblemDetailActivity.class);
                        intent.putExtra("problem", problem);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(problemAdapter);
            }
        });

        // Handle the click event of the navigation button
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Handle item selections from the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });
    }
}