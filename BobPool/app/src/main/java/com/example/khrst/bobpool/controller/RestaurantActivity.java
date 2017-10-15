package com.example.khrst.bobpool.controller;

import android.app.ListActivity;
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
    private static List<String> pools;
    private Button getList;
    //public static Pool pool;
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

               pools = new ArrayList<>();
               databaseReference.child("pool").addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                       for (DataSnapshot child : children) {
                           String childValue = child.child("title").getValue().toString();
                           pools.add(childValue);
                           //pools.add(pool.toString());
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
               ArrayAdapter<String> adapter = new ArrayAdapter<String>(RestaurantActivity.this,
                       android.R.layout.simple_list_item_1, pools);
               lv.setAdapter(adapter);
           }
       });
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RestaurantActivity.this,
//                android.R.layout.simple_list_item_1, pools);
//        lv.setAdapter(adapter);
    }
}