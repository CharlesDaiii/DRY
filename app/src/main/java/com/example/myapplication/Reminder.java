package com.example.myapplication;

import java.io.Serializable;

public class Reminder implements Serializable {
    String category;
    String time;
    String title;
    String detail;
    String date;

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
