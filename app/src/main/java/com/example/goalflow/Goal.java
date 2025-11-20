package com.example.goalflow;

public class Goal {
    public String name;
    public String amount;
    public String unit;
    public String frequency;
    public String notifyTime;
    public int streak = 0;
    public int progress = 0;
    public Goal(String name, String amount, String unit, String frequency, String notifyTime) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.frequency = frequency;
        this.notifyTime = notifyTime;
    }
}
