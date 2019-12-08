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

import static com.example.myapplication.MainActivity.reminders;

public class ShowReminder extends AppCompatActivity {

    TextView reminderTitle;
    TextView reminderDetail;
    Button startTime;
    Button startDate;
    Button endTime;
    Button endDate;
    Button returnBotton;
    private int mHour;
    private int mMinute;
    private RadioGroup priorityGroup;
    private RadioGroup categoryGroup;
    Reminder reminder;

    private TextView show;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_detail);
        /**--------------------初始化------------------------**/
        reminderTitle = findViewById(R.id.reminderTitle);
        reminderDetail = findViewById(R.id.reminderDetail);
        startTime = findViewById(R.id.reminderStartTime);
        startDate = findViewById(R.id.reminderStartDate);
        endTime = findViewById(R.id.reminderEndTime);
        endDate = findViewById(R.id.reminderEndDate);
        returnBotton = findViewById(R.id.returnButton);
        endDate.setVisibility(View.GONE);
        endTime.setVisibility(View.GONE);
        startTime.setVisibility(View.GONE);
        startDate.setVisibility(View.GONE);
        priorityGroup = findViewById(R.id.priorityGroup);
        categoryGroup = findViewById(R.id.categoryGroup);
        RadioButton chooseTime = findViewById(R.id.chooseTime);

        Intent intent = getIntent();
        reminder = (Reminder) intent.getExtras().getSerializable("reminder");
        //Reminder reminder = (Reminder) getApplicationContext();
        /**-------------------------------------------------**/

        reminderDetail.setText(reminder.getDetail());
        reminderTitle.setText(reminder.getTitle());

        chooseTime.setOnClickListener(view ->  {
            System.out.println("start");
            startTime.setVisibility(View.VISIBLE);
            startDate.setVisibility(View.VISIBLE);
            endTime.setVisibility(View.VISIBLE);
            endDate.setVisibility(View.VISIBLE);
            System.out.println(startDate.getVisibility());
            System.out.println(startDate.getVisibility() == (View.VISIBLE));
            if (startDate.getVisibility() == (View.VISIBLE)) {
                System.out.println("begin");
                startTime.setOnClickListener(v -> chooseTime());
                endTime.setOnClickListener(v -> chooseTime());
                startDate.setOnClickListener(v -> chooseDate());
                endDate.setOnClickListener(v -> chooseDate());
            }
        });




        /**
         * 选择priority的部分；
         */
        //setContentView(R.layout.activity_main);
        priorityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = findViewById(checkedId);
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

        returnBotton.setOnClickListener(view -> goToMainActivity(reminder));

    }


    private void chooseTime() {
        Calendar cale2 = Calendar.getInstance();
        new TimePickerDialog(ShowReminder.this, (view, hourOfDay, minute) -> {
            String result = "";
            result += "The Time You Choose Is:" + hourOfDay + " : " + minute;
            mHour = hourOfDay;
            mMinute = minute;
            reminder.setTime(mHour + " : " +mMinute);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true).show();
    }

    private void chooseDate() {
        System.out.println("choose Date");
        Calendar cale1 = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            //这里获取到的月份需要加上1
            String result = "";
            result += "The Day You Choose Is" + (monthOfYear+1) +"/ "+dayOfMonth+"/ " + year;
            reminder.setTime((monthOfYear+1) +"/ "+dayOfMonth+"/ " + year);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
                ,cale1.get(Calendar.YEAR)
                ,cale1.get(Calendar.MONTH)
                ,cale1.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void goToMainActivity(Reminder reminder) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
