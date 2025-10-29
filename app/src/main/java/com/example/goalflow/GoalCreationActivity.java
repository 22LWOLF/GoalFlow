package com.example.goalflow;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GoalCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goal_creation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText goalNameET = findViewById(R.id.goalNameET);
        EditText amountOfTimeET = findViewById(R.id.amountOfTimeET);
        Spinner timeUnitSpinner = findViewById(R.id.timeUnitSpinner);
        Spinner notificationFrequencySpinner = findViewById(R.id.notificationFrequencySpinner);
        EditText notificationTimeET = findViewById(R.id.notificationTimeET);
        Button submitBTN = findViewById(R.id.submitBTN);

        ArrayAdapter<CharSequence> timeUnitAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.time_units,
                android.R.layout.simple_spinner_item
        );
        timeUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeUnitSpinner.setAdapter(timeUnitAdapter);

        ArrayAdapter<CharSequence> freqAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.notification_frequencies,
                android.R.layout.simple_spinner_item
        );
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notificationFrequencySpinner.setAdapter(freqAdapter);
    }

}