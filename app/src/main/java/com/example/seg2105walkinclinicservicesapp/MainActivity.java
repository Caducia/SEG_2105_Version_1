package com.example.seg2105walkinclinicservicesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDataBase;
    private Button registerButton;
    private FirebaseUser user;
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance();

        //Setting the button and interactive elements of the page
        registerButton = findViewById(R.id.registerButton);

        //If user is already logged in
        user = mAuth.getCurrentUser();

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);


    }

    public void registerUser(View v){
        Intent registerIntent = new Intent(this, SelectRegistrationActivity.class);
        startActivity(registerIntent);
    }

    public void loginUser(View v){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();


        final boolean login = false;
        user = null;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Authentication passed.", Toast.LENGTH_SHORT).show();
//                            updateUI(user);
//                            login = false;
                            Intent loginIntent = new Intent(MainActivity.this, WelcomeScreen.class);
                            loginIntent.putExtra("email" , emailInput.getText().toString());
                            loginIntent.putExtra("password" , passwordInput.getText().toString());
                            startActivity(loginIntent);


                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                    }


                });
    }


}
