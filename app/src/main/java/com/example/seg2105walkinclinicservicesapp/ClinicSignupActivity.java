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

public class ClinicSignupActivity extends AppCompatActivity {
    private EditText idEditText;
    private EditText clinicNameEditText;
    private EditText clinicEmailEditText;
    private EditText clinicPhoneNumberEditText;
    private EditText clinicPasswordEditText;
    private EditText clinicConfirmPasswordEditText;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clinic_signup_activity);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        idEditText = findViewById(R.id.clinicIDInput);
        clinicNameEditText = findViewById(R.id.clinicNameInput);
        clinicEmailEditText = findViewById(R.id.clinicEmailInput);
        clinicPhoneNumberEditText = findViewById(R.id.clinicPhoneInput);
        clinicPasswordEditText = findViewById(R.id.clinicPasswordInput);
        clinicConfirmPasswordEditText = findViewById(R.id.clinicConfirmPasswordInput);

        databaseReference = mDatabase.getInstance().getReference("Clinic");
    }

    public void signUpClinic(View v){
        final String clinicID = idEditText.getText().toString();
        final String clinicName = clinicNameEditText.getText().toString();
        final String clinicPhoneNumber = clinicPhoneNumberEditText.getText().toString();
        final String clinicEmail = clinicEmailEditText.getText().toString();
        final String clinicPassword = clinicPasswordEditText.getText().toString();

        // Might need later
        //final String password = passwordEditText.getText().toString();



        mAuth.createUserWithEmailAndPassword(clinicEmail,clinicPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Clinic clinic = new Clinic(clinicID, clinicName, clinicEmail, clinicPhoneNumber, clinicPassword);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    FirebaseDatabase.getInstance().getReference("ClinicEmployees")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(clinic).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ClinicSignupActivity.this, "User successfully registered", Toast.LENGTH_LONG).show();
                                finish();
                            }else{
                                Toast.makeText(ClinicSignupActivity.this, "Failed to Register User", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    });

                }
            }
        });

    }
}
