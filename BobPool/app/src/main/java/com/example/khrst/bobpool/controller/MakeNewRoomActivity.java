package com.example.khrst.bobpool.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import com.example.khrst.bobpool.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MakeNewRoomActivity extends AppCompatActivity {
    private Button addButton;
    private ToggleButton ampmButton;
    private EditText newName, newDate, newTime, newCapacity, newNotes;
    private Calendar newCalendar;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("user");
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_room);
        newDate = (EditText) findViewById(R.id.newDate);
        newName = (EditText) findViewById(R.id.newname);
        newTime = (EditText) findViewById(R.id.newTime);
        newCapacity = (EditText) findViewById(R.id.maxnum);
        newNotes = (EditText) findViewById(R.id.notes);
        newCalendar = Calendar.getInstance();
        ampmButton = (ToggleButton) findViewById(R.id.ampmbutton);
        addButton = (Button) findViewById(R.id.addroombutton);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                newCalendar.set(Calendar.YEAR, year);
                newCalendar.set(Calendar.MONTH, month);
                newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(newDate);

            }
        };
        newDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MakeNewRoomActivity.this, date, newCalendar
                        .get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MakeNewRoomActivity.this, RestaurantActivity.class));
            }
        });
    }
    private void updateLabel(EditText t) {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        t.setText(sdf.format(newCalendar.getTime()));
    }
}
