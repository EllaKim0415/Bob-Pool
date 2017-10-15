package com.example.khrst.bobpool.controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.khrst.bobpool.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class RestaurantActivity extends AppCompatActivity {
    private ListView lv;
    private static List<String> pools = new ArrayList<>();
    private Button getList;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        lv = (ListView) findViewById(R.id.list);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        getList = (Button) findViewById(R.id.listButton);
        getList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               databaseReference.child(MapsActivity.getSelectedRestaurant()).addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                       for (DataSnapshot child : children) {
                           pools.add(child.child("title").getValue().toString() + "(" +
                                   child.child("currentNum").getValue().toString() + "/" + child.
                                   child("capacity").getValue().toString() + ")");
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
               ArrayAdapter<String> adapter = new ArrayAdapter<String>(RestaurantActivity.this,
                       android.R.layout.simple_list_item_1, pools);
               lv.setAdapter(adapter);
               pools = new ArrayList<String>();
           }
       });
        Button makeNewPoolButton = (Button) findViewById(R.id.makeNewPoolButton);
        makeNewPoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RestaurantActivity.this, MakeNewRoomActivity.class));
            }
        });
        getList.performClick();
    }
}