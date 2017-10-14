package com.example.khrst.bobpool;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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
    private ListView lv;
    List<String> pools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        lv = (ListView) findViewById(R.id.lv);
        pools = new ArrayList<String>();
        DatabaseReference poolsDatabaseRef;
        poolsDatabaseRef = FirebaseDatabase.getInstance().getReference("pools");
        //poolsDatabase.child("pool1").getKey();
        poolsDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pools.add(dataSnapshot.getValue().toString());
                //System.out.println(dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                System.out.println("child changed");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("child removed");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                System.out.println("child moved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("cancelled");
            }
        });
        if (pools.isEmpty()) {
            System.out.println("000015");
        }
        for (String i: pools) {
            System.out.println(i);
            System.out.println("0");
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RestaurantActivity.this,
//                android.R.layout.simple_list_item_1, pools);
//        lv.setAdapter(adapter);
    }
}