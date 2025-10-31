package com.example.goalflow;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    EditText nameET, emailET;
    Switch notificationSwitch, reminderSwitch;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        notificationSwitch = findViewById(R.id.notificationSwitch);
        reminderSwitch = findViewById(R.id.reminderSwitch);
        backBtn = findViewById(R.id.backBtn);

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String msg = isChecked ? "Notifications enabled" : "Notifications Disabled";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        reminderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String msg = isChecked ? "Daily reminders enabled" : "Daily reminders disabled";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        backBtn.setOnClickListener(v -> {
            String name = nameET.getText().toString().trim();
            String email = emailET.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill out name and email", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Settings saved successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, DashboardActivity.class));
            }
        });
    }
}
