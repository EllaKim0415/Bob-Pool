package com.example.khrst.bobpool.controller;

import android.content.Intent;
import android.os.DropBoxManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.khrst.bobpool.R;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.
                Intent mInHome = new Intent(EntryActivity.this, LoginActivity.class);
                EntryActivity.this.startActivity(mInHome);
                EntryActivity.this.finish();
            }
        }, 3000);
    }
}
