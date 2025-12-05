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
        TextView achievementTV = findViewById(R.id.achievementNameTV); // reuse one TextView
        ArrayList<Goal> savedGoals = GoalStorage.loadGoals(this);

        StringBuilder achievements = new StringBuilder();
        boolean anyUnlocked = false;

        for (Goal g : savedGoals) {
            int progress = g.progress;

            if (progress >= 30) {
                achievements.append("🥇 30-").append(g.unit).append(" Master!\n")
                        .append("Completed a full month of ").append(g.unit.toLowerCase()).append("!\n\n");
                anyUnlocked = true;
            }
            if (progress >= 14) {
                achievements.append("🏅 Two-Week Streak!\n")
                        .append("Keep going! 14 ").append(g.unit.toLowerCase()).append(" in a row!\n\n");
                anyUnlocked = true;
            }
            if (progress >= 7) {
                achievements.append("🏆 First 7-").append(g.unit).append(" Streak!\n")
                        .append("Completed your first full week of ").append(g.unit.toLowerCase()).append("!\n\n");
                anyUnlocked = true;
            }
            if (progress >= 3) {
                achievements.append("🎉 3-").append(g.unit).append(" Streak!\n")
                        .append("Great start! 3 ").append(g.unit.toLowerCase()).append(" in a row!\n\n");
                anyUnlocked = true;
            }
        }

        if (!anyUnlocked) {
            achievements.append("🏆 No achievements yet!\nStart tracking your goals to unlock achievements.");
        }

        achievementTV.setText(achievements.toString().trim());
    }
}


