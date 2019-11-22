package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class showReminder extends AppCompatActivity {

    TextView reminderTitle;
    TextView reminderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_detail);
        /**--------------------初始化------------------------**/
        reminderTitle = findViewById(R.id.reminderTitle);
        reminderDetail = findViewById(R.id.reminderDetail);
        Intent intent = getIntent();
        Reminder reminder = (Reminder) intent.getExtras().getSerializable("reminder");
        /**-------------------------------------------------**/

        reminderDetail.setText(reminder.getDetail());
        reminderTitle.setText(reminder.getTitle());
    }
}
