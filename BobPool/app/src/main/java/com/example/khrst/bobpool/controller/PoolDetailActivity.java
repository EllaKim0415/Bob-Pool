package com.example.khrst.bobpool.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.example.khrst.bobpool.R;
import com.example.khrst.bobpool.model.Pool;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PoolDetailActivity extends AppCompatActivity {
    private TextView restaurant, name, date, time, currentNum, maxNum, notes;
    private static Pool selected;
    private String selectedKey;
    private Button joinButton;
    private String key;
    private int current;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    public String access = "";
    public String secret = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_detail);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(MapsActivity.getSelectedRestaurant());
        selected = RestaurantActivity.getSelectedPool();
        selectedKey = RestaurantActivity.getSelectedKey();

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
                current = Integer.parseInt(currentNum.getText().toString());
                if(current < Integer.parseInt(maxNum.getText().toString())) {
                    key = RestaurantActivity.getSelectedKey();
                    current++;
                    databaseReference.child(key).child("currentNum").setValue(String.valueOf(current));

                    Toast.makeText(PoolDetailActivity.this,"Successfully joined",Toast.LENGTH_LONG).show();
/*
                    DatabaseReference awsRef = databaseReference.getParent().child("aws").child("access");
                    awsRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            access = dataSnapshot.getValue().toString();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    DatabaseReference awsRef2 = databaseReference.getParent().child("aws").child("secret");
                    awsRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            secret = dataSnapshot.getValue().toString();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    */

                    AWSCredentials awsCredentials =new BasicAWSCredentials(access, secret);
                    AmazonSNSClient snsClient = new AmazonSNSClient(awsCredentials);
                    String message = "Someone just joined! " + "Now there are " + current + " people.";
                    String phoneNumber = "+1" + selected.getPhoneNumber().toString();
                    Map<String, MessageAttributeValue> smsAttributes =
                            new HashMap<String, MessageAttributeValue>();
                    //<set SMS attributes>
                    sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
                    startActivity(new Intent(getApplicationContext(), RestaurantActivity.class));
                } else {
                    Toast.makeText(PoolDetailActivity.this,"This pool capacity is full :(",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), RestaurantActivity.class));
                }
            }
        });
    }

    public static void sendSMSMessage(final AmazonSNSClient snsClient, final String message,
                                      final String phoneNumber, final Map<String, MessageAttributeValue> smsAttributes) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    PublishResult result = snsClient.publish(new PublishRequest()
                            .withMessage(message)
                            .withPhoneNumber(phoneNumber)
                            .withMessageAttributes(smsAttributes));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
