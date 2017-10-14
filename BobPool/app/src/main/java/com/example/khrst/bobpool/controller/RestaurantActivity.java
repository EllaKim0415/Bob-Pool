package com.example.khrst.bobpool.controller;

import android.app.ListActivity;
import android.os.Bundle;

import com.example.khrst.bobpool.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;

public class RestaurantActivity extends ListActivity {
    ListView lv;
    static List<String> pools = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        super.onCreate(savedInstanceState);
        lv = (ListView) findViewById(R.id.lv);
        databaseReference.child("pool").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> poolData = dataSnapshot.getChildren();
                for (DataSnapshot pool : poolData) {
                    pools.add(pool.child("title").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (pools.isEmpty()) {
            System.out.println("EMPTY");
        }
        for (String i: pools) {
            System.out.println(i);
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RestaurantActivity.this,
//                android.R.layout.simple_list_item_1, pools);
//        lv.setAdapter(adapter);
    }
}