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
        answerEditText = findViewById(R.id.answer_edit_text);

        // Retrieve intent data
        Intent intent = getIntent();
        correctAnswer = intent.getStringExtra("CORRECT_ANSWER"); // Retrieve the correct answer
        if (correctAnswer == null) {
            correctAnswer = ""; // Default to empty string if null
        }

        // Set up the button click listener
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }


    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();

        // Log the user input and correct answer for debugging
        System.out.println("User Answer: " + userAnswer);
        System.out.println("Correct Answer: " + correctAnswer);

        // Check if the user's answer matches the correct answer
        if (correctAnswer != null && userAnswer.equals(correctAnswer.trim())) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show();
        }
    }

}