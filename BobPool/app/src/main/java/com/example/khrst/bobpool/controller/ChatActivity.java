package com.example.khrst.bobpool.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khrst.bobpool.R;
import com.example.khrst.bobpool.model.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by com93 on 10/15/2017.
 */

public class ChatActivity extends FragmentActivity {
    //Firebase Database Variables:
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference userRef;
    private DatabaseReference messages_database;
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> mFirebaseAdapter;
    //Bottom navigation
    private BottomBar mBottomBar;
    //Toast
    public Toast thetoast;

    private EditText chatusername;
    //Chat UI
    private Button sendButton;
    private RecyclerView messageRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        if (isNetworkAvailable()) {
            createChat();
        }
    }

    private void createChat() {
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
