package com.example.goalflow;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {
    //this is stuff for the images
    int[] images = {R.drawable.sunset1, R.drawable.sunrise, R.drawable.daytime};
    int currentIndex = 0;
    Handler handler = new Handler();
    Runnable runnable;

    TextView welcomeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ArrayList<Goal> savedGoals = GoalStorage.loadGoals(this);


//part of the recyler view
        RecyclerAdapter reminderServer = new RecyclerAdapter(savedGoals);
        RecyclerView remindersRV = findViewById(R.id.remindersRV);
        remindersRV.setAdapter(reminderServer);
        remindersRV.setLayoutManager(new LinearLayoutManager(this));

        //Buttons
        ImageButton achievementsBTN = findViewById(R.id.achievementsBTN);
        ImageButton settingsBTN = findViewById(R.id.settingsBTN);
        ImageButton goalCreateBTN = findViewById(R.id.goalCreateBTN);
        ImageButton trackerBTN = findViewById(R.id.trackerBTN);
        ImageView welcomeImage;

        welcomeTV = findViewById(R.id.welcomeTV);

         int time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
         String welcomeMSG;

         if(time < 12) {
             welcomeMSG = "Good Morning!";
         }
         else if(time < 18) {
             welcomeMSG = "Good Afternoon!";
         }
         else {
             welcomeMSG = "Good Evening";
         }

         welcomeTV.setText(welcomeMSG);

//Changes the image every now and then
        welcomeImage = findViewById(R.id.welcomeImage);
        welcomeImage.setImageResource(images[currentIndex]);
        runnable = new Runnable() {
            public void run() {
                currentIndex = (currentIndex + 1) % images.length;
                welcomeImage.setImageResource(images[currentIndex]);
                handler.postDelayed(this, 20000);

            }
        };
        handler.postDelayed(runnable, 20000);

        achievementsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AchievementsActivity.class);
                startActivity(intent);
            }
        });
        settingsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        trackerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, TrackerActivity.class);
                startActivity(intent);
            }
        });
        goalCreateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, GoalCreationActivity.class);
                startActivity(intent);
            }
        });



    }
}
