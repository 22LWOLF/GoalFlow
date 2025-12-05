package com.example.goalflow;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GoalStorage {

    private static final String PREF_NAME = "GoalFlowPrefs";
    private static final String KEY_GOALS = "goals";

    // --- Save a single new goal (append to list) ---
    public static void saveGoal(Context context, Goal goal) {
        ArrayList<Goal> goals = loadGoals(context);
        goals.add(goal);

        saveGoals(context, goals);
    }

    // --- Save entire list (used by TrackerActivity) ---
    public static void saveGoals(Context context, ArrayList<Goal> goals) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(goals);

        editor.putString(KEY_GOALS, json);
        editor.apply();
    }

    // --- Load all goals ---
    public static ArrayList<Goal> loadGoals(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_GOALS, null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Goal>>() {}.getType();

        if (json == null) {
            return new ArrayList<>();
        }

        ArrayList<Goal> goals;

        try {
            goals = gson.fromJson(json, type);
        } catch (Exception e) {
            // fallback for any corrupted JSON
            return new ArrayList<>();
        }

        // --- Make sure older goals have the new fields ---
        for (Goal g : goals) {
            if (g.maxProgress <= 0) g.maxProgress = 100;  // fallback
            if (g.progress < 0) g.progress = 0;
            if (g.streak < 0) g.streak = 0;
        }

        return goals;
    }
}
