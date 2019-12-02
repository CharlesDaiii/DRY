package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ShowReminder extends AppCompatActivity {

    TextView reminderTitle;
    TextView reminderDetail;
    private int mHour;
    private int mMinute;
    private RadioGroup priorityGroup;
    private TextView show;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_detail);
        /**--------------------初始化------------------------**/
        reminderTitle = findViewById(R.id.reminderTitle);
        reminderDetail = findViewById(R.id.reminderDetail);
        Button startTime = findViewById(R.id.reminderStartTime);
        Button startDate = findViewById(R.id.reminderStartDate);
        Button endTime = findViewById(R.id.reminderEndTime);
        Button endDate = findViewById(R.id.reminderEndDate);
        endDate.setVisibility(View.GONE);
        endTime.setVisibility(View.GONE);
        startTime.setVisibility(View.GONE);
        startDate.setVisibility(View.GONE);
        RadioGroup priorityGroup = findViewById(R.id.priorityGroup);
        RadioGroup categoryGroup = findViewById(R.id.categoryGroup);
        RadioButton chooseTime = findViewById(R.id.chooseTime);

        Intent intent = getIntent();
        Reminder reminder = (Reminder) intent.getExtras().getSerializable("reminder");
        /**-------------------------------------------------**/

        reminderDetail.setText(reminder.getDetail());
        reminderTitle.setText(reminder.getTitle());

        chooseTime.setOnClickListener(view ->  {
            startTime.setVisibility(View.VISIBLE);
            startDate.setVisibility(View.VISIBLE);
            endTime.setVisibility(View.VISIBLE);
            endDate.setVisibility(View.VISIBLE);
        });
        /**
         * goodbye.setOnClickListener(new View.OnClickListener() {
         *     @Override
         *     public void onClick(final View v) {
         *         // Change the label's text
         *         label.setText("Goodbye.");
         *     }
         * });
         */

        if (startDate.getVisibility() == View.VISIBLE) {
            startTime.setOnClickListener(v -> chooseTime());
            endTime.setOnClickListener(v -> chooseTime());
            startDate.setOnClickListener(v -> chooseDate());
            endDate.setOnClickListener(v -> chooseDate());
        }


        /**
         * 选择priority的部分；
         */
        //setContentView(R.layout.activity_main);
        priorityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "you choose" + radbtn.getText(), Toast.LENGTH_LONG).show();
                reminder.setCategory(radbtn.getText().toString());
            }
        });

        /**
         * 选择category的部分；
         */
        categoryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "you choose" + radbtn.getText(), Toast.LENGTH_LONG).show();
                reminder.setCategory(radbtn.getText().toString());
            }
        });

    }


    private void chooseTime() {
        Calendar cale2 = Calendar.getInstance();
        new TimePickerDialog(ShowReminder.this, (view, hourOfDay, minute) -> {
            String result = "";
            result += "The Time You Choose Is:" + hourOfDay + " : " + minute;
            mHour = hourOfDay;
            mMinute = minute;
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true).show();
    }

    private void chooseDate() {
        Calendar cale1 = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            //这里获取到的月份需要加上1
            String result = "";
            result += "The Day You Choose Is" + (monthOfYear+1) +", "+dayOfMonth+", " + year;
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
                ,cale1.get(Calendar.YEAR)
                ,cale1.get(Calendar.MONTH)
                ,cale1.get(Calendar.DAY_OF_MONTH)).show();
    }

}
