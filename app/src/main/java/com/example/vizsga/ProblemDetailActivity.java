package com.example.vizsga;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProblemDetailActivity extends AppCompatActivity {

    private TextView problemTextView;
    private EditText answerEditText;
    private Button submitButton;
    private ImageView feedbackImageView;
    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        problemTextView = findViewById(R.id.problem_text_view);
        answerEditText = findViewById(R.id.answer_edit_text);
        submitButton = findViewById(R.id.button_submit);
        feedbackImageView = findViewById(R.id.feedback_image_view); // Initialize ImageView

        // Retrieve the Problem object passed from MainActivity
        Problem problem = (Problem) getIntent().getSerializableExtra("problem");

        if (problem != null) {
            // Display the problem
            problemTextView.setText(problem.toString());

            // Calculate the correct answer based on the problem
            correctAnswer = String.valueOf(problem.calculateAnswer());
        } else {
            Toast.makeText(this, "Problem data not found.", Toast.LENGTH_SHORT).show();
            finish();
        }

        submitButton.setOnClickListener(v -> checkAnswer());
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();

        if (correctAnswer != null) {
            try {
                double userAnswerDouble = Double.parseDouble(userAnswer);
                double correctAnswerDouble = Double.parseDouble(correctAnswer.trim());

                if (userAnswer.equals(correctAnswer.trim())) {
                    Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
                    feedbackImageView.setImageResource(R.drawable.correct); // Set image for correct answer
                } else {
                    Toast.makeText(this, getString(R.string.incorrect_answer), Toast.LENGTH_SHORT).show();
                    feedbackImageView.setImageResource(R.drawable.incorrect); // Set image for incorrect answer
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Answer could not be processed. Please reload the problem.", Toast.LENGTH_SHORT).show();
        }

        feedbackImageView.setVisibility(View.VISIBLE); // Show the image
    }
}
