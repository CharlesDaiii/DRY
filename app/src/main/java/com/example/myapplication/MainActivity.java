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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private RequestQueue mQueue;
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
        Button button = findViewById(R.id.button);
        /**------------------------------------------**/
        showAll();
        System.out.println("miao");

        newReminder.setOnClickListener(v -> {
            inputReminder();
        });

        mQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myapi();
            }
        });

    }
    void myapi() {
        final TextView textView = findViewById(R.id.text);

        String url ="https://api.myjson.com/bins/kp9wz";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String firstName = employee.getString("firstname");
                                int age = employee.getInt("age");
                                String mail = employee.getString("mail");

                                textView.append(firstName + ", " + age + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        mQueue.add(request);
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
