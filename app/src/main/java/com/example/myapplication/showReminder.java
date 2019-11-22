package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class showReminder extends AppCompatActivity {

    TextView reminderTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_detail);
        reminderTitle = findViewById(R.id.reminderTitle);
        Intent intent = getIntent();
        Reminder reminder = (Reminder) intent.getExtras().getSerializable("reminder");
        reminderTitle.setText(reminder.getTitle());
    }
}
