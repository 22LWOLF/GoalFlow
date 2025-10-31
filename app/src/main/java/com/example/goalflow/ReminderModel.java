package com.example.goalflow;

import java.util.ArrayList;

public class ReminderModel {

    private static ReminderModel instance;
    public ArrayList<Reminder> reminderList;

    private static ReminderModel remModel = null;
    public static ReminderModel getRemModel(){
        if(remModel == null) remModel = new ReminderModel();
        return remModel;
    }
    public class Reminder {
        private String title;
        private String description;
        public Reminder(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
    private ReminderModel(){

    }


}
