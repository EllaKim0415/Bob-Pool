package com.example.khrst.bobpool.controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.khrst.bobpool.R;
import com.example.khrst.bobpool.model.Pool;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class RestaurantActivity extends AppCompatActivity {
    private ListView lv;
    private static List<String> pools = new ArrayList<>();
    private static List<String> keys = new ArrayList<>();
//    private static List<Pool> poolList = new ArrayList<>();
    static int placed = 0;
    private static String selectedKey;
//    private static Pool selectedPool;
    private Button getList;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        lv = (ListView) findViewById(R.id.list);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(MapsActivity.getSelectedRestaurant());
        getList = (Button) findViewById(R.id.listButton);
        getList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               databaseReference.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                       for (DataSnapshot child : children) {
                           keys.add(child.getKey());
                           //poolList.add(child.getValue(Pool.class));
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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override

            public void onItemClick(AdapterView<?> parent, View view,

                                    final int position, long id) {

                placed = position;

                databaseReference.addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        selectedKey = keys.get(placed);

//                        selectedPool = poolList.get(placed);


//
//                        for (DataSnapshot child : children) {
//
//                            Pool childValue = child.getValue(Pool.class);
//
//                            if (childValue.getPhoneNumber() == selected.getPhoneNumber()) {
//
//                                selectedPool = childValue;
//
//                            }
//
//                        }

                    }



                    @Override

                    public void onCancelled(DatabaseError databaseError) {



                    }

                });

                startActivity(new Intent(getApplicationContext(), PoolDetailActivity.class));

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
    public static String getSelectedKey() {
        return selectedKey;
    }
//    public static Pool getSelectedPool() {
//        return selectedPool;
//    }
}