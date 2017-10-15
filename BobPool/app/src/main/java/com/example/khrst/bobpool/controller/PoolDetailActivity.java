package com.example.khrst.bobpool.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.khrst.bobpool.R;

public class PoolDetailActivity extends AppCompatActivity {
    private TextView restaurant, name, date, time, currentNum, maxNum, notes;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restaurant = (TextView) findViewById(R.id.restaurantText);
        name = (TextView) findViewById(R.id.nameText);
        date = (TextView) findViewById(R.id.dateText);
        time = (TextView) findViewById(R.id.timeText);
        currentNum = (TextView) findViewById(R.id.currentNumText);
        maxNum = (TextView) findViewById(R.id.maxCapText);
        notes = (TextView) findViewById(R.id.notesText);
        setContentView(R.layout.activity_pool_detail);
    }
}
