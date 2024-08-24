package com.example.vizsga;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import com.example.vizsga.Problem;

public class ProblemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        TextView problemTextView = findViewById(R.id.problem_text);

        // Retrieve problem data from intent
        Problem problem = (Problem) getIntent().getSerializableExtra("problem");
        if (problem != null) {
            problemTextView.setText(problem.toString());
        }
    }
}
