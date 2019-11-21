package com.example.myapplication;

public class Reminder {
    String category;
    String startTime;
    String endTime;
    Reminder(String setCategory, String setStartTime, String setEndTIme) {
        category = setCategory;
        startTime = setStartTime;
        endTime = setEndTIme;
    }
    void setCategory(String changeCategory) {
        category = changeCategory;
    }
    void setStartTime(String changeStartTime) {
        startTime = changeStartTime;
    }
    void setEndTime(String changeEndTime) {
        endTime = changeEndTime;
    }
    String getCategory() {
        return category;
    }
    String getStartTime() {
        return startTime;
    }
    String getEndTime() {
        return getEndTime();
    }
}
