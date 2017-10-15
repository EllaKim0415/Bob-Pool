package com.example.khrst.bobpool.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.DatePickerDialog;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import com.example.khrst.bobpool.R;
import com.example.khrst.bobpool.model.Pool;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

public class MakeNewRoomActivity extends AppCompatActivity {
    private Button addButton;
    private ToggleButton ampmButton;
    private EditText newName, newDate, newTime, newCapacity, newNotes, currentNum;
    private Calendar newCalendar;
    private String amPm = "am";
    private Pool newPool;
    private String restaurant = MapsActivity.getSelectedRestaurant();

    private SwitchDateTimeDialogFragment dateTimeFragment;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference(restaurant);
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_room);
        newDate = (EditText) findViewById(R.id.newDate);
        newName = (EditText) findViewById(R.id.newname);
        newTime = (EditText) findViewById(R.id.newTime);
        newCapacity = (EditText) findViewById(R.id.maxnum);
        currentNum = (EditText) findViewById(R.id.currentNum);
        newNotes = (EditText) findViewById(R.id.notes);
        newCalendar = Calendar.getInstance();

        ampmButton = (ToggleButton) findViewById(R.id.ampmbutton);
        addButton = (Button) findViewById(R.id.addroombutton);
        makeDateTimeFragment();

        newDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Show
                dateTimeFragment.show(getSupportFragmentManager(), "dialog_time");
            }
        });

        newTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
// Show
                dateTimeFragment.show(getSupportFragmentManager(), "dialog_time");
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newName.getText().toString().equals("")
                        || newDate.getText().toString().equals("")
                        || newTime.getText().toString().equals("")
                        || newCapacity.getText().toString().equals("")
                        || currentNum.getText().toString().equals("")) {

                    Context context = getApplicationContext();
                    CharSequence text = "Need more information to create a pool.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else {
                    if (ampmButton.isChecked()) {
                        amPm = "pm";
                    }
                    newPool = new Pool(LoginActivity.getPhoneNumber(),
                            restaurant, newName.getText().toString(),
                            newDate.getText().toString(), (newTime.getText().toString() + amPm),
                            newNotes.getText().toString(), newCapacity.getText().toString(),
                            currentNum.getText().toString());
                    newPooling(newPool);
                }
                startActivity(new Intent(MakeNewRoomActivity.this, RestaurantActivity.class));
            }
        });
    }

    private void newPooling(Pool pool) {
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue("pool");
        Toast.makeText(MakeNewRoomActivity.this, "Successfully added.", Toast.LENGTH_LONG).show();
    }

    private void updateLabel(EditText t) {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        t.setText(sdf.format(newCalendar.getTime()));
    }

    private void makeDateTimeFragment() {
        dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                "Set Time",
                "OK",
                "Cancel"
        );

// Assign values
        dateTimeFragment.startAtCalendarView();
        dateTimeFragment.set24HoursMode(false);
        dateTimeFragment.setMinimumDateTime(new GregorianCalendar(2015, Calendar.JANUARY, 1).getTime());
        dateTimeFragment.setMaximumDateTime(new GregorianCalendar(2025, Calendar.DECEMBER, 31).getTime());
        dateTimeFragment.setDefaultDateTime(new GregorianCalendar().getTime());

// Define new day and month format
        try {
            dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("dd MMMM", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
        }

// Set listener
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                newCalendar.set(Calendar.YEAR, dateTimeFragment.getYear());
                newCalendar.set(Calendar.MONTH, dateTimeFragment.getMonth());
                newCalendar.set(Calendar.DAY_OF_MONTH, dateTimeFragment.getDay());
                updateLabel(newDate);

                int hour = date.getHours() % 12;
                newTime.setText(hour + ":" + date.getMinutes());
                if (date.getHours() > 12) {
                    ampmButton.setChecked(true);
                } else {
                    ampmButton.setChecked(false);
                }
            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Date is get on negative button click
            }
        });
    }
}

