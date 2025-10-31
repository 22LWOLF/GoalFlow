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

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    //this is stuff for the images
    int[] images = {R.drawable.sunset1, R.drawable.sunrise, R.drawable.daytime};
    int currentIndex = 0;
    Handler handler = new Handler();
    Runnable runnable;

private ArrayList<String> data = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        //for the recyler view
        data = new ArrayList<String>(); data.add("Reminder");

        ImageButton achievementsBTN = findViewById(R.id.achievementsBTN);
        ImageButton settingsBTN = findViewById(R.id.settingsBTN);
        ImageButton goalCreateBTN = findViewById(R.id.goalCreateBTN);
        ImageButton trackerBTN = findViewById(R.id.trackerBTN);
        ImageView welcomeImage;
        TextView welcomeTV;
        TextClock timeView;

//Changes the image every now and then
        welcomeImage = findViewById(R.id.welcomeImage);
        runnable = new Runnable(){
            public void run() {
                currentIndex = (currentIndex + 1) % images.length;
                welcomeImage.setImageResource(images[currentIndex]);
                handler.postDelayed(this, 100000);

            }
        };
        handler.postDelayed(runnable, 100000);

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

//part of the recyler view
    RecyclerAdapter reminderServer = new RecyclerAdapter(data);
        RecyclerView remindersRV = findViewById(R.id.remindersRV);
        remindersRV.setAdapter(reminderServer);
        remindersRV.setLayoutManager(new LinearLayoutManager(this));
    }
}
