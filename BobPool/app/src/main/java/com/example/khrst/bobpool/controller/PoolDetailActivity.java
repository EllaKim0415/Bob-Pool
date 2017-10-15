package com.example.khrst.bobpool.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.khrst.bobpool.R;
import com.example.khrst.bobpool.model.Pool;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PoolDetailActivity extends AppCompatActivity {
    private TextView restaurant, name, date, time, currentNum, maxNum, notes;
    private static Pool selected;
    private String selectedKey;
    private Button joinButton;
    private String key;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_detail);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(MapsActivity.getSelectedRestaurant());
        //selected = RestaurantActivity.getSelectedPool();
        selectedKey = RestaurantActivity.getSelectedKey();

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                for (DataSnapshot child : children) {
//                    if (child.getKey().equals(key)) {
//                        selected = child.getValue(Pool.class);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        restaurant = (TextView) findViewById(R.id.restaurantText);
        restaurant.setText(selected.getRestaurant());
        name = (TextView) findViewById(R.id.nameText);
        name.setText(selected.getTitle());
        date = (TextView) findViewById(R.id.dateText);
        date.setText(selected.getDate());
        time = (TextView) findViewById(R.id.timeText);
        time.setText(selected.getTime());
        currentNum = (TextView) findViewById(R.id.currentNumText);
        currentNum.setText(selected.getCurrentNum());
        maxNum = (TextView) findViewById(R.id.maxCapText);
        maxNum.setText(selected.getCapacity());
        notes = (TextView) findViewById(R.id.notesText);
        if (!selected.getNotes().equals("")) {
            notes.setText(selected.getNotes());
        }

        joinButton = (Button) findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(currentNum.getText().toString()) < Integer.parseInt(maxNum.getText().toString())) {

                }
            }
        });
    }
}
