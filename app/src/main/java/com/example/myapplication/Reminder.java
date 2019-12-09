package com.example.myapplication;

import android.app.Application;

import java.io.Serializable;

public class Reminder extends Application implements Serializable  {
    String category = "";
    String time;
    String title;
    String detail;
    String date;
    String priority = "";

    Reminder() {}
    Reminder (String setTitle) {
        title = setTitle;
    }
    void setCategory(String changeCategory) {
        System.out.println("setC: " + category);
        category = changeCategory;
    }
    void setPriority(String changePriority) {
        priority = changePriority;
    }
    void setStartTime(String changeTime) {
        time = changeTime;
    }
    void setStartDate(String changeDate) {
        date = changeDate;
    }
    void setTitle(String changeTitle) {
        title = changeTitle;
    }
    void setDetail(String changeDetail) {
        detail = changeDetail;
    }
    String getDetail() {
        return detail;
    }
    String getTitle() {
        return title;
    }
    String getCategory() {
        return category;
    }
    String getPriority() {
        return priority;
    }
    String getStartTime() {
        if (time == null) {
            return null;
        }
        return time;
    }
    String getStartDate() {
        if (date == null) {
            return null;
        }
        return date;
    }
}
