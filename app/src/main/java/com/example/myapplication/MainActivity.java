package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
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

import com.google.android.gms.common.util.Hex;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;
import static android.graphics.BlendMode.COLOR;


public class MainActivity extends AppCompatActivity {


    /**--------------初始化-----------------------**/
    public static ArrayList<Reminder> reminders = new ArrayList<>();
    Reminder test = new Reminder();
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TextView alarmTv;
    private int mHour;
    private int mMinute;
    private int mSeconds;
    private LinearLayout oneReminder;
    private String category = "";
    private static String lastCategory = "";
    /**------------------------------------------**/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**--------------初始化-----------------------**/
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        if (category != null) {
            lastCategory = category;
        }
        category = lastCategory;
        System.out.println("this time the category is " + category);
        Button newReminder = findViewById(R.id.newReminder);
        /**------------------------------------------**/
        showAll();
        System.out.println("miao");

        newReminder.setOnClickListener(v -> {
            inputReminder();
        });


    }

    private void inputReminder() {
        final EditText et = new EditText(this);
        et.setSingleLine();
        new AlertDialog.Builder(this).setTitle("create new reminder")
                .setIcon(android.R.drawable.star_on)
                .setView(et)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String title = et.getText().toString();
                        newReminder(title);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void newReminder(String setTitle) {
        /**--------------初始化-----------------------**/
        LinearLayout toDoList= findViewById(R.id.toDoList);
        Reminder aReminder = new Reminder(setTitle);
        /**------------------------------------------**/
        reminders.add(aReminder);
        toDoList.removeAllViews();
        showAll();

    }
    private void showAll() {
        LinearLayout toDoList= findViewById(R.id.toDoList);
        int count = 0;
        for (Reminder each: reminders) {
            if (!category.equals(each.getCategory()) && !category.equals(Category.scheduled)) {
                continue;
            }
            final int thiscount = count;
            View toDoListChunk = getLayoutInflater().inflate(R.layout.to_do_list_chunk, toDoList, false);
            TextView title = toDoListChunk.findViewById(R.id.title);
            TextView subtitle = toDoListChunk.findViewById(R.id.subtitle);
            RadioButton isFinish = toDoListChunk.findViewById(R.id.isFinish);
            oneReminder = toDoListChunk.findViewById(R.id.oneReminder);
            oneReminder.setOnClickListener(v -> {
                System.out.println(thiscount);
                showReminder(thiscount);
            });
            title.setText(each.getTitle());
            if (each.getPriority().equals("None") || each.getPriority().equals("")) {
                subtitle.setText(" ");
                System.out.println("MainActivity: none or null");
            } else {
                if (each.getPriority().equals("High")) {
                    subtitle.setText("!!!");
                    subtitle.setTextColor(Color.RED);
                    System.out.println("MainActivity: high");
                }
                if (each.getPriority().equals("Medium")) {
                    subtitle.setText("!!");
                    subtitle.setTextColor(Color.YELLOW);
                    System.out.println("MainActivity: medium");

                }
                if (each.getPriority().equals("Low")) {
                    subtitle.setText("!");
                    subtitle.setTextColor(Color.GREEN);
                    System.out.println("MainActivity: low");
                }
            }
            toDoList.addView(toDoListChunk);
            isFinish.setOnClickListener(v -> {
                title.setVisibility(View.GONE);
                subtitle.setVisibility(View.GONE);
                isFinish.setVisibility(View.GONE);
                reminders.remove(each);
            });
            count++;
        }
    }
    private void showReminder(int index) {
        Intent i = new Intent(this, ShowReminder.class);
        i.putExtra("index", index);
        startActivity(i);
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent;
            myIntent = new Intent(MainActivity.this, Menu.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
