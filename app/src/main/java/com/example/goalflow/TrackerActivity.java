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

import java.util.ArrayList;

public class TrackerActivity extends AppCompatActivity {

    private ArrayList<Goal> goals;
    private int currentIndex = 0;
    private Goal currentGoal;

    private TextView goalNameTV;
    private TextView streakTV;
    private ProgressBar progressBar;
    private Button updateProgressBTN;
    private Button nextGoalBTN;

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

        // UI refs
        goalNameTV = findViewById(R.id.goalNameTV);
        streakTV = findViewById(R.id.streakTV);
        progressBar = findViewById(R.id.progressBar);
        updateProgressBTN = findViewById(R.id.updateProgressBTN);
        nextGoalBTN = findViewById(R.id.nextGoalBTN);

        Button backButton = findViewById(R.id.backBTN);
        backButton.setOnClickListener(v -> finish());

        Button addGoalButton = findViewById(R.id.addGoalBTN);
        addGoalButton.setOnClickListener(v ->
                startActivity(new Intent(TrackerActivity.this, GoalCreationActivity.class))
        );

        // Load saved goals
        loadGoals();

        // Next goal: cycle through list
        nextGoalBTN.setOnClickListener(v -> {
            if (goals == null || goals.isEmpty()) return;
            currentIndex = (currentIndex + 1) % goals.size();
            currentGoal = goals.get(currentIndex);
            displayCurrentGoal();
        });

        // Update progress + streak
        updateProgressBTN.setOnClickListener(v -> {
            if (currentGoal == null) return;

            // ⭐ Increase progress by 1 (or whatever increment you want)
            currentGoal.progress = Math.min(currentGoal.progress + 1, currentGoal.maxProgress);
            progressBar.setProgress(currentGoal.progress);

            // ⭐ Streak increases only when reaching full completion
            if (currentGoal.progress >= currentGoal.maxProgress) {
                currentGoal.streak = currentGoal.streak + 1;
                updateProgressBTN.setEnabled(false);
                updateProgressBTN.setText("Goal Complete!");
            }

            streakTV.setText("🔥 " + currentGoal.progress + " " + currentGoal.unit + " Streak");


            // persist all goals back to storage
            GoalStorage.saveGoals(this, goals);
        });
    }

    private void loadGoals() {
        goals = GoalStorage.loadGoals(this);

        if (goals == null || goals.isEmpty()) {
            // no saved goals
            goalNameTV.setText("No goals yet");

            // crash fix
            streakTV.setText("🔥 0 Streak");

            progressBar.setProgress(0);
            updateProgressBTN.setEnabled(false);
            nextGoalBTN.setEnabled(false);
            return;
        }

        // ensure index within bounds
        if (currentIndex < 0 || currentIndex >= goals.size()) currentIndex = 0;

        currentGoal = goals.get(currentIndex);
        displayCurrentGoal();

        // enable buttons
        updateProgressBTN.setEnabled(true);
        nextGoalBTN.setEnabled(goals.size() > 1);
    }

    private void displayCurrentGoal() {
        if (currentGoal == null) return;

        goalNameTV.setText(currentGoal.name != null ? currentGoal.name : "Untitled Goal");

        // ⭐ Use currentProgress instead of streak
        streakTV.setText("🔥 " + currentGoal.progress + " " + currentGoal.unit + " Streak");

        progressBar.setMax(currentGoal.maxProgress);        // ensure max is correct
        progressBar.setProgress(currentGoal.progress);

        if (currentGoal.progress >= currentGoal.maxProgress) {
            updateProgressBTN.setEnabled(false);
            updateProgressBTN.setText("Goal Complete!");
        } else {
            updateProgressBTN.setEnabled(true);
            updateProgressBTN.setText("Update Progress");
        }
    }

}

