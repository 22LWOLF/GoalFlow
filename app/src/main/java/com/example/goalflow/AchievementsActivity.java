package com.example.goalflow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_achievements);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back button
        Button backBTN = findViewById(R.id.backBTN);
        backBTN.setOnClickListener(v -> finish());

        // Load achievements
        loadAchievements();
    }

    private void loadAchievements() {
        TextView achievementNameTV = findViewById(R.id.achievementNameTV);
        TextView achievementDescTV = findViewById(R.id.achievementDescTV);

        ArrayList<Goal> savedGoals = GoalStorage.loadGoals(this);

        // Default message if no achievements
        StringBuilder names = new StringBuilder();
        StringBuilder descs = new StringBuilder();
        boolean anyUnlocked = false;

        for (Goal g : savedGoals) {
            if (g.streak >= 30) {
                names.append("🥇 30-").append(g.unit).append(" Master!\n");
                descs.append("Completed a full month of ").append(g.unit.toLowerCase()).append("!\n\n");
                anyUnlocked = true;
            }
            if (g.streak >= 14) {
                names.append("🏅 Two-Week Streak!\n");
                descs.append("Keep going! 14 ").append(g.unit.toLowerCase()).append("s in a row!\n\n");
                anyUnlocked = true;
            }
            if (g.streak >= 7) {
                names.append("🏆 First 7-").append(g.unit).append(" Streak!\n");
                descs.append("Completed your first full week of ").append(g.unit.toLowerCase()).append(" progress!\n\n");
                anyUnlocked = true;
            }
            if (g.streak >= 3) {
                names.append("🎉 3-").append(g.unit).append(" Streak!\n");
                descs.append("Great start! 3 ").append(g.unit.toLowerCase()).append("s in a row!\n\n");
                anyUnlocked = true;
            }
        }

        if (!anyUnlocked) {
            names.append("🏆 No achievements yet!");
            descs.append("Start tracking your goals to unlock achievements.");
        }

        achievementNameTV.setText(names.toString().trim());
        achievementDescTV.setText(descs.toString().trim());
    }
}
