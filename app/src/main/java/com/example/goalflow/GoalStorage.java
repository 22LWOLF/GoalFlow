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

    public static void saveGoal(Context context, Goal goal) {
        ArrayList<Goal> goals = loadGoals(context);
        goals.add(goal);

        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(goals);

        editor.putString(KEY_GOALS, json);
        editor.apply();
    }

    public static ArrayList<Goal> loadGoals(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_GOALS, null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Goal>>(){}.getType();

        if (json == null) {
            return new ArrayList<>();
        }

        return gson.fromJson(json, type);
    }
}
