package com.example.seg2105walkinclinicservicesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientSignupActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText studentNumberEditText;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        firstNameEditText = findViewById(R.id.firstNameInput);
        lastNameEditText = findViewById(R.id.lastNameInput);
        emailEditText = findViewById(R.id.emailInput);
        phoneNumberEditText = findViewById(R.id.phoneInput);
        passwordEditText = findViewById(R.id.passwordInput);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordInput);
        studentNumberEditText = findViewById(R.id.studentNumberInout);

        databaseReference = mDatabase.getInstance().getReference("Patient");
    }

    public void signUpPatient(View v){
        final String firstName = firstNameEditText.getText().toString();
        final String lastName = lastNameEditText.getText().toString();
        final String phoneNumber = phoneNumberEditText.getText().toString();
        final String studentNumber = studentNumberEditText.getText().toString();
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();



        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Patient patient = new Patient(firstName, lastName, studentNumber, email, password,phoneNumber);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    FirebaseDatabase.getInstance().getReference("Students")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PatientSignupActivity.this, "User successfully registered", Toast.LENGTH_LONG).show();
                                finish();
                            }else{
                                Toast.makeText(PatientSignupActivity.this, "Failed to Register User", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    });

                }
            }
        });

    }
}

