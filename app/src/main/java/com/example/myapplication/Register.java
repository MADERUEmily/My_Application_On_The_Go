package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    ProgressBar progressBar2;
    EditText userEditText, FullName, passEditText, Address;
    TextView LogInTextView;
    Button SignInButton;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar2 = findViewById(R.id.progressBar2);
        userEditText = findViewById(R.id.userEditText);
        FullName = findViewById(R.id.FullName);
        passEditText = findViewById(R.id.passEditText);
        Address = findViewById(R.id.Address);
        LogInTextView = findViewById(R.id.LogInTextView);
        SignInButton = findViewById(R.id.SignInButton);

        ///connect database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("users");
        fAuth = FirebaseAuth.getInstance();

       
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = userEditText.getText().toString().trim();
                String password = passEditText.getText().toString().trim();
                final String fullName = FullName.getText().toString().trim();
                final String address = Address.getText().toString().trim();

              //  SignInButton.onEditorAction(EditorInfo.IME_ACTION_DONE);
                if (TextUtils.isEmpty(email)) {
                    userEditText.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(fullName)) {
                    userEditText.setError("Full name is required.");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    userEditText.setError("Address is required.");
                    return;
                }
                if (password.length() < 10) {
                    passEditText.setError("Password must be longer or equal to 10 characters");
                    return;
                }

                progressBar2.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(fullName, address);
                                    myRef.child(fAuth.getUid()).setValue(user);
                                    Toast.makeText(Register.this, "Registered",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, LogIn.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    progressBar2.setVisibility(View.GONE);
                                    Toast.makeText(Register.this, "Authentication failed " + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }


        });

        LogInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
