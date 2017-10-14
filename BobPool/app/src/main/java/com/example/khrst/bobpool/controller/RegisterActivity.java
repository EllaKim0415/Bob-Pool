package com.example.khrst.bobpool.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khrst.bobpool.R;
import com.example.khrst.bobpool.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText passwordEdit;
    private EditText phoneEdit;
    private EditText confirmEdit;
    private EditText addressEdit;
    private EditText usernameEdit;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("user");
    FirebaseAuth firebaseAuth;

    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private static final String TAG = "EMAIL/PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameEdit = (EditText) findViewById(R.id.editTextFirstName);
        lastNameEdit = (EditText) findViewById(R.id.editTextLastName);
        phoneEdit = (EditText) findViewById(R.id.editText3Phone);
        addressEdit = (EditText) findViewById(R.id.editTextAddress);
        usernameEdit = (EditText) findViewById(R.id.editText6username);
        passwordEdit = (EditText) findViewById(R.id.editText9password);
        confirmEdit = (EditText) findViewById(R.id.editText11confirm);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNameEdit.getText().toString().equals("")
                        || lastNameEdit.getText().toString().equals("")
                        || usernameEdit.getText().toString().equals("")
                        || confirmEdit.getText().toString().equals("")
                        || addressEdit.getText().toString().equals("")) {
                    System.out.println("TTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEEEESSSSSSSSSSSSSSSSSSSSTTTTTTTTTTTTTTTTTTTTT");

                    Context context = getApplicationContext();
                    CharSequence text = "Need more registration information.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
                System.out.println("TTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSSSSSSSSSSSTTTTTTTTTTTTTTTTTTTT2");
                registerUser(usernameEdit.getText().toString(), passwordEdit.getText().toString());
            }
        });

    }

    public boolean isValidEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    private void registerUser(String e, String p) {
        System.out.println("TTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSSSSSSSSSSSTTTTTTTTTTTTTTTTTTTT3");
        if (!isValidEmail(e)) {
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(p)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(p)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if (p.length() < 6) {
            Toast.makeText(this, "Password is too short. Must be longer than 6", Toast.LENGTH_LONG). show();
        }

        firebaseAuth.createUserWithEmailAndPassword(e, p)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        //checking if success
                        if(task.isSuccessful()){
                            User newUser = new User(usernameEdit.getText().toString(), firstNameEdit.getText().toString(),
                                    lastNameEdit.getText().toString(), phoneEdit.getText().toString(), addressEdit.getText().toString(),
                                    passwordEdit.getText().toString());


                            //Commment out when junit testing
                            String id = databaseReference.push().getKey();
                            databaseReference.child(id).setValue(newUser);

                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                            Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Email already exists",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
