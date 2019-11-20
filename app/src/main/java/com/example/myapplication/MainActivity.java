package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> things = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newReminder = findViewById(R.id.newReminder);
        newReminder.setOnClickListener(v -> {
            System.out.println("hello");
            newReminder();
        });
        Button chooseDate = findViewById(R.id.chooseDate);
        chooseDate.setOnClickListener(v -> {
            chooseDate();
        });
        Button chooseTime = findViewById(R.id.chooseTime);
        chooseTime.setOnClickListener(v -> {
            chooseTime();
        });
    }
    private void chooseTime() {
        Calendar cale2 = Calendar.getInstance();
        new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String result = "";
                result += "您选择的时间是:"+hourOfDay+"时"+minute+"分";
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true).show();
    }
    private void chooseDate() {
        Calendar cale1 = Calendar.getInstance();
        new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                //这里获取到的月份需要加上1哦~
                String result = "";
                result += "你选择的是"+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日";
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
        ,cale1.get(Calendar.YEAR)
        ,cale1.get(Calendar.MONTH)
        ,cale1.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void newReminder() {
        System.out.println("hello");
        LinearLayout toDoList= findViewById(R.id.toDoList);
        toDoList.removeAllViews();
        EditText thingsToDo = findViewById(R.id.thingsToDo);
        things.add(thingsToDo.getText().toString());
        for (String each: things) {
            View toDoListChunk = getLayoutInflater().inflate(R.layout.to_do_list_chunk, toDoList, false);
            System.out.println(thingsToDo.getText().toString());
            TextView title = toDoListChunk.findViewById(R.id.title);
            TextView subtitle = toDoListChunk.findViewById(R.id.subtitle);
            title.setText(each);
            subtitle.setText(LocalDateTime.now().toString());
            toDoList.addView(toDoListChunk);
            RadioButton isFinish = toDoListChunk.findViewById(R.id.isFinish);
            isFinish.setOnClickListener(v -> {
                title.setVisibility(View.GONE);
                subtitle.setVisibility(View.GONE);
                isFinish.setVisibility(View.GONE);
                things.remove(each);
            });
        }
    }
}
