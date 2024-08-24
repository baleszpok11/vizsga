package com.example.vizsga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProblemAdapter problemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load JSON data and set up RecyclerView
        JSONUtils.loadProblemsFromUrl(this, new JSONUtils.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(List<Problem> problems) {
                problemAdapter = new ProblemAdapter(problems, new ProblemAdapter.OnProblemClickListener() {
                    @Override
                    public void onProblemClick(Problem problem) {
                        // Handle problem click event
                        Intent intent = new Intent(MainActivity.this, ProblemDetailActivity.class);
                        intent.putExtra("PROBLEM", problem);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(problemAdapter);
            }
        });

        // Setup sidebar menu and other UI elements
    }
}