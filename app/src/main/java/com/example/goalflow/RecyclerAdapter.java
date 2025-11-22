package com.example.goalflow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends
    RecyclerView.Adapter<RecyclerAdapter.ReminderViewHolder> {


    private ArrayList<Goal> goals;

    public RecyclerAdapter(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    public static class ReminderViewHolder
            extends RecyclerView.ViewHolder {
        TextView goalName, goaltime;

        public ReminderViewHolder(View itemView) {
            super(itemView);
            goalName = itemView.findViewById(R.id.reminderListTV);
            goaltime = itemView.findViewById(R.id.reminderTV);
        }
    }


    public ReminderViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerreminder_view, parent, false);
        return new ReminderViewHolder(v);
    }

    public void onBindViewHolder(
            @NonNull ReminderViewHolder holder,
            int position) {
        Goal goal = goals.get(position);

        holder.goalName.setText(goal.name);
        holder.goaltime.setText("Notify Time: " + goal.notifyTime);
    }

    @Override
    public int getItemCount() {

        return goals.size();
    }
}


