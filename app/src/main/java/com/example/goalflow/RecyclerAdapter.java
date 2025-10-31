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





    public static class ReminderViewHolder
            extends RecyclerView.ViewHolder {
        public ReminderViewHolder(View v) {
            super(v);
        }
    }

    private ArrayList<String> reminders;

    public RecyclerAdapter(ArrayList<String> reminders) {
        super();
        this.reminders = reminders;
    }

    public RecyclerAdapter.ReminderViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerreminder_view, parent, false);
        ReminderViewHolder vh = new ReminderViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(
            @NonNull ReminderViewHolder holder,
            int position) {
        TextView reminderListTV = holder.itemView.findViewById(R.id.reminderListTV);
        reminderListTV.setText(reminders.get(position));
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }
}


