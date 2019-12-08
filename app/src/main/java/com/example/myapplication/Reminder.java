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
    Reminder (String setTitle, String setCategory, String setStartTime, String setEndTIme) {
        title = setTitle;
        category = setCategory;
        time = setEndTIme;
    }
    void setCategory(String changeCategory) {
        category = changeCategory;
    }
    void setPriority(String changePriority) {
        priority = changePriority;
    }
    void setTime(String changeTime) {
        time = changeTime;
    }
    void setDate(String changeDate) {
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
    String getTime() {
        if (time == null) {
            return "";
        }
        return time;
    }
    String getDate() {
        if (date == null) {
            return "";
        }
        return date;
    }
}
