package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class ShowReminder extends AppCompatActivity {

    TextView reminderTitle;
    TextView reminderDetail;
    private int mHour;
    private int mMinute;
    private RadioGroup priorityGroup;
    private TextView show;
    Reminder test;
    Reminder reminder;
    int index;
    Intent intent;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_detail);


        /**--------------------初始化------------------------**/
        reminderTitle = findViewById(R.id.reminderTitle);
        reminderDetail = findViewById(R.id.reminderDetail);
        Button startTime = findViewById(R.id.reminderStartTime);
        Button startDate = findViewById(R.id.reminderStartDate);
        startTime.setVisibility(View.GONE);
        startDate.setVisibility(View.GONE);
        RadioGroup priorityGroup = findViewById(R.id.priorityGroup);
        RadioGroup categoryGroup = findViewById(R.id.categoryGroup);
        RadioButton business = findViewById(R.id.category_businessTrip);
        RadioButton exercising = findViewById(R.id.category_exercising);
        RadioButton study = findViewById(R.id.category_study);
        RadioButton highP = findViewById(R.id.priority_High);
        RadioButton mediumP = findViewById(R.id.priority_Medium);
        RadioButton lowP = findViewById(R.id.priority_Low);
        RadioButton noneP = findViewById(R.id.priority_None);
        RadioButton chooseTime = findViewById(R.id.chooseTime);

        intent = getIntent();
        index = intent.getIntExtra("index", 0);
        reminder = MainActivity.reminders.get(index);
        /**-------------------------------------------------**/

        reminderDetail.setText(reminder.getDetail());
        reminderTitle.setText(reminder.getTitle());

        if (reminder.getCategory().equals("study")) {
            study.setChecked(true);
        }
        if (reminder.getCategory().equals("exercising")) {
            exercising.setChecked(true);
        }
        if (reminder.getCategory().equals("businessTrip")) {
            business.setChecked(true);
        }
        if (reminder.getPriority().equals("High")) {
            highP.setChecked(true);
        }
        if (reminder.getPriority().equals("Medium")) {
            mediumP.setChecked(true);
        }
        if (reminder.getPriority().equals("Low")) {
            lowP.setChecked(true);
        }
        if (reminder.getPriority().equals("None")) {
            noneP.setChecked(true);
        }
        if (reminder.getStartTime() != null ||  reminder.getStartDate() != null) {
            chooseTime.setChecked(true);
            startTime.setVisibility(View.VISIBLE);
            startDate.setVisibility(View.VISIBLE);
            if (reminder.getStartTime() != null) {
                startTime.setText(reminder.getStartTime());
            }
            if (reminder.getStartDate() != null) {
                startDate.setText(reminder.getStartDate());
            }
        }
        reminderDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                reminder.setDetail(reminderDetail.getText().toString());
                MainActivity.reminders.set(index, reminder);
            }
        });


        chooseTime.setOnClickListener(view ->  {
            startTime.setVisibility(View.VISIBLE);
            startDate.setVisibility(View.VISIBLE);
        });

        startTime.setOnClickListener(v -> chooseTime(startTime));
        startDate.setOnClickListener(v -> chooseDate(startDate));

        /**
         * 选择priority的部分；
         */
        //setContentView(R.layout.activity_main);
        priorityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "you choose " + radbtn.getText(), Toast.LENGTH_LONG).show();
                reminder.setPriority(radbtn.getText().toString());
                System.out.println(radbtn.getText().toString());
                MainActivity.reminders.set(index, reminder);
            }
        });

        /**
         * 选择category的部分；
         */
        categoryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "you choose " + radbtn.getText(), Toast.LENGTH_LONG).show();
                reminder.setCategory(radbtn.getText().toString());
                MainActivity.reminders.set(index, reminder);
            }
        });

    }


    private void chooseTime(Button selectedButton) {
        Calendar cale2 = Calendar.getInstance();
        new TimePickerDialog(ShowReminder.this, (view, hourOfDay, minute) -> {
            String result = "";
            result += "the time you choose is: " + hourOfDay + " : " + minute;
            mHour = hourOfDay;
            mMinute = minute;
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            selectedButton.setText(hourOfDay + " : " + minute);
            reminder.setStartTime(hourOfDay + " : " + minute);
            MainActivity.reminders.set(index, reminder);
        }, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true).show();
    }

    private void chooseDate(Button selectedButton) {
        Calendar cale1 = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            //这里获取到的月份需要加上1
            String result = "";
            result += "the day you choose is " + (monthOfYear+1) +" / "+dayOfMonth+" / " + year;
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            selectedButton.setText((monthOfYear+1) +" / "+dayOfMonth+" / " + year);
            reminder.setStartDate((monthOfYear+1) +" / "+dayOfMonth+" / " + year);
            MainActivity.reminders.set(index, reminder);
        }
                ,cale1.get(Calendar.YEAR)
                ,cale1.get(Calendar.MONTH)
                ,cale1.get(Calendar.DAY_OF_MONTH)).show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent;
            myIntent = new Intent(ShowReminder.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
