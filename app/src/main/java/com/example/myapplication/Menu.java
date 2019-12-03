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
                Intent intent = new Intent();
                category = Category.scheduled;
                intent.putExtra("category", category);
            }
        });

        businessTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                category = Category.businessTrip;
                intent.putExtra("category", category);
            }
        });

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                category = Category.upcoming;
                intent.putExtra("category", category);
            }
        });

        exercising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                category = Category.exercising;
                intent.putExtra("category", category);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                category = Category.study;
                intent.putExtra("category", category);
            }
        });
    }
}
