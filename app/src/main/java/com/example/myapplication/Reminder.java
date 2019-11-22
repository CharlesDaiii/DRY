package com.example.myapplication;

import java.io.Serializable;

public class Reminder implements Serializable {
    String category;
    String startTime;
    String endTime;
    String title;
    String detail;

    Reminder() {}
    Reminder (String setTitle) {
        title = setTitle;
    }
    Reminder (String setTitle, String setCategory, String setStartTime, String setEndTIme) {
        title = setTitle;
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
    String getStartTime() {
        return startTime;
    }
    String getEndTime() {
        return endTime;
    }
}
