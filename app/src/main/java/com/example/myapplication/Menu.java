package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    ImageView scheduled;
    ImageView businessTrip;
    ImageView upcoming;
    ImageView exercising;
    ImageView study;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        scheduled = findViewById(R.id.scheduled);
        businessTrip = findViewById(R.id.businessTrip);
        upcoming = findViewById(R.id.upcoming);
        exercising = findViewById(R.id.exercising);
        study = findViewById(R.id.study);

        scheduled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = Category.scheduled;
                goToReminder(category);
            }
        });

        businessTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = Category.businessTrip;
                goToReminder(category);
            }
        });

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = Category.upcoming;
                goToReminder(category);
            }
        });

        exercising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = Category.exercising;
                goToReminder(category);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = Category.study;
                goToReminder(category);
            }
        });
    }
    public void goToReminder(String category) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
