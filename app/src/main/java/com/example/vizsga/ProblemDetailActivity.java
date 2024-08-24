package com.example.vizsga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProblemDetailActivity extends AppCompatActivity {

    private TextView problemTextView;
    private EditText answerEditText;
    private Button submitButton;

    private String problemText;
    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        // Initialize views
        problemTextView = findViewById(R.id.problem_text_view);
        answerEditText = findViewById(R.id.answer_edit_text);
        submitButton = findViewById(R.id.submit_button);

        // Get the data from the Intent
        Intent intent = getIntent();
        problemText = intent.getStringExtra("PROBLEM");
        correctAnswer = intent.getStringExtra("CORRECT_ANSWER");

        // Set the problem text
        problemTextView.setText(problemText);

        // Set a click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();

        // Check if the user's answer matches the correct answer
        if (userAnswer.equals(correctAnswer)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show();
        }
    }
}