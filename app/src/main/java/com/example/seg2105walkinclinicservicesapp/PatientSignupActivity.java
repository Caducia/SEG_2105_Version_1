package com.example.seg2105walkinclinicservicesapp;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PatientSignupActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText studentNumberEditText;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String studentNumber;
    private String email;
    private String password;

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
        firstName = firstNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();
        phoneNumber = phoneNumberEditText.getText().toString();
        studentNumber = studentNumberEditText.getText().toString();
        email = emailEditText.getText().toString();


        password = passwordEditText.getText().toString();



        if(confirmPasswordEditText.getText().toString().trim().equals(password.trim()) && firstName.length() >= 2 && lastName.length() >= 2){
            try{
                Integer.parseInt(phoneNumber);
                if(phoneNumber.length() >= 10){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Patient patient = new Patient(firstName.trim(), lastName.trim(), studentNumber.trim(), email.trim(), password.trim(),phoneNumber.trim());
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
            }catch(Exception e){
                Toast.makeText(PatientSignupActivity.this, "Make sure a proper number is used", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(PatientSignupActivity.this, "Make sure passwords match", Toast.LENGTH_LONG).show();
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

