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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ClinicSignupActivity extends AppCompatActivity {
    private EditText idEditText;
    private EditText clinicNameEditText;
    private EditText clinicEmailEditText;
    private EditText clinicPhoneNumberEditText;
    private EditText clinicPasswordEditText;
    private EditText clinicConfirmPasswordEditText;

    private String clinicID;
    private String clinicName;
    private String clinicPhoneNumber;
    private String clinicEmail;
    private String clinicPassword;

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


        // Might need later
        //final String password = passwordEditText.getText().toString();


        clinicEmail = clinicEmailEditText.getText().toString();
        clinicPassword = clinicPasswordEditText.getText().toString();
        clinicID = idEditText.getText().toString();
        clinicName = clinicNameEditText.getText().toString();
        clinicPhoneNumber = clinicPhoneNumberEditText.getText().toString();

        if(clinicConfirmPasswordEditText.getText().toString().trim().equals(clinicPassword.trim()) && clinicName.length() >= 2) {
            try {
                Integer.parseInt(clinicPhoneNumber);
                if (clinicPhoneNumber.length() >= 10) {
                    mAuth.createUserWithEmailAndPassword(clinicEmail,clinicPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if(task.isSuccessful()){
                                clinicPassword = encryptString(clinicPassword);
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
            } catch (Exception e) {
                Toast.makeText(ClinicSignupActivity.this, "Make sure a correct phone number is used", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(ClinicSignupActivity.this, "Make Sure passwords match", Toast.LENGTH_LONG).show();
        }

    }

    public static String encryptString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] byteDigestions = messageDigest.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger number = new BigInteger(1, byteDigestions);

            // Convert message digest into hex value
            String hashtext = number.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
