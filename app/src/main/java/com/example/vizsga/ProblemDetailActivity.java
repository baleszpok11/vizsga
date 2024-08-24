package com.example.vizsga;

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

    private Problem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        problemTextView = findViewById(R.id.problem_text_view);
        answerEditText = findViewById(R.id.answer_edit_text);
        submitButton = findViewById(R.id.submit_button);

        // Retrieve Problem object from Intent
        problem = (Problem) getIntent().getSerializableExtra("PROBLEM");

        if (problem != null) {
            problemTextView.setText(problem.toString());
        }

        // Handle the submit button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void checkAnswer() {
        String userAnswerStr = answerEditText.getText().toString().trim();

        // Log the user input and correct answer for debugging
        System.out.println("User Answer: " + userAnswerStr);
        System.out.println("Correct Answer: " + problem.getCorrectAnswer());

        try {
            int userAnswer = Integer.parseInt(userAnswerStr);
            int correctAnswer = problem.getCorrectAnswer();

            // Check if the user's answer matches the correct answer
            if (userAnswer == correctAnswer) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
        }
    }
}