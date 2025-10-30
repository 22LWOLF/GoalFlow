package com.example.goalflow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        // dummy data
        loadAchievements();
    }

    private void loadAchievements() {
        // Stubbed example data — simulating what might come from a database later
        String achievementName = "🏆 First 7-Day Streak!";
        String achievementDesc = "Completed your first full week of progress!";

        TextView achievementNameTV = findViewById(R.id.achievementNameTV);
        TextView achievementDescTV = findViewById(R.id.achievementDescTV);

        achievementNameTV.setText(achievementName);
        achievementDescTV.setText(achievementDesc);
    }
}
