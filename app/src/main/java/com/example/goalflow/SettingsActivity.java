package com.example.goalflow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

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

        nameET.setText(SettingsStorage.loadName(this));
        emailET.setText(SettingsStorage.loadEmail(this));
        notificationSwitch.setChecked(SettingsStorage.loadNotifications(this));
        reminderSwitch.setChecked(SettingsStorage.loadReminders(this));


        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SettingsStorage.saveNotifications(this, isChecked);
            Toast.makeText(this, isChecked ? "Notifications enabled" : "Notifications disabled", Toast.LENGTH_SHORT).show();
        });

        reminderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SettingsStorage.saveReminders(this, isChecked);
            Toast.makeText(this, isChecked ? "Daily reminders enabled" : "Daily reminders disabled", Toast.LENGTH_SHORT).show();
        });

        backBtn.setOnClickListener(v -> {
            String name = nameET.getText().toString().trim();
            String email = emailET.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill out name and email", Toast.LENGTH_SHORT).show();
            } else {
                SettingsStorage.saveName(this, name);
                SettingsStorage.saveEmail(this, email);
                Toast.makeText(this, "Settings saved successfully!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
