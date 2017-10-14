package com.example.khrst.bobpool.controller;

/**
 * Created by com93 on 10/14/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khrst.bobpool.R;
import com.wang.avi.AVLoadingIndicatorView;

public class Location_Checker_Screen extends AppCompatActivity {
    TextView text;
    LocationManager tester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location__checker__screen);
        if (isLocationEnabled(this)) {
            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                    startAnim();
                }

                public void onFinish() {
                    endAnim();
                }
            }.start();

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Location Service");
            alert.setMessage("Do you wanna enable Location Service"); // Want to enable?
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            alert.create().show();
            TextView text = (TextView) findViewById(R.id.editText);
            text.setText("Click map if Location Service Turned On");
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Location_Checker_Screen.this, MapsActivity.class));
                }
            });
        }
    }


    public boolean isLocationEnabled(Context context) {
        tester = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        text = (TextView) findViewById(R.id.notification);
        if (!tester.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            text.setText("Location Service Not Turned On");
            return false;
        }
        text.setText("Checking Location Service");
        return true;
    }
    void startAnim() {
        AVLoadingIndicatorView avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.show();
    }

    void endAnim() {
        AVLoadingIndicatorView avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.hide();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}