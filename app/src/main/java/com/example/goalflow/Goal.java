package com.example.goalflow;

public class Goal {
    public String name;
    public String unit;
    public String frequency;
    public String notifyTime;

    public int maxProgress;   // total required to finish goal
    public int progress;      // current progress
    public int streak;        // how many times completed

    public Goal(String name, String amountStr, String unit, String freq, String notif) {
        this.name = name;

        // Convert input amount to the goal's maximum progress
        try {
            this.maxProgress = Integer.parseInt(amountStr);
        } catch (Exception e) {
            this.maxProgress = 100; // fallback if invalid input
        }

        this.unit = unit;
        this.frequency = freq;
        this.notifyTime = notif;

        this.progress = 0;
        this.streak = 0;
    }
}
