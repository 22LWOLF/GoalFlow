package com.example.goalflow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tracker);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button backButton = findViewById(R.id.backBTN);
        backButton.setOnClickListener(v -> finish());

        Button addGoalButton = findViewById(R.id.addGoalBTN);
        addGoalButton.setOnClickListener(v -> {
            Intent intent = new Intent(TrackerActivity.this, GoalCreationActivity.class);
            startActivity(intent);
        });

        // Load fake goal data
        loadGoals();

        // progress updates
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button updateProgressButton = findViewById(R.id.updateProgressBTN);
        TextView streakText = findViewById(R.id.streakTV);

        updateProgressButton.setOnClickListener(v -> {
            int currentProgress = progressBar.getProgress();
            if (currentProgress < 100) {
                int newProgress = Math.min(currentProgress + 20, 100);
                progressBar.setProgress(newProgress);

                // Update streak number
                String streakTextValue = streakText.getText().toString();
                int streakNumber = Integer.parseInt(streakTextValue.replaceAll("\\D+", ""));
                streakNumber++;
                streakText.setText("🔥 " + streakNumber + " Day Streak");
            } else {
                updateProgressButton.setEnabled(false);
                updateProgressButton.setText("Goal Complete!");
            }
        });
    }

    private void loadGoals() {
        // dummy data
        String goalName = "Exercise Daily";
        int streak = 5;
        int progress = 60;

        TextView goalNameTV = findViewById(R.id.goalNameTV);
        TextView streakTV = findViewById(R.id.streakTV);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        goalNameTV.setText(goalName);
        streakTV.setText("🔥 " + streak + " Day Streak");
        progressBar.setProgress(progress);
    }
}
