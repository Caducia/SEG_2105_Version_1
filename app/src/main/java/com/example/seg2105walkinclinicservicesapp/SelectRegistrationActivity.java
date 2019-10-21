package com.example.seg2105walkinclinicservicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SelectRegistrationActivity extends AppCompatActivity {

    private Button returnButton;
    private LinearLayout patientSignup;
    private LinearLayout clinicSignup;
    private Intent clinicRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        //This button handles deleting the activity
        returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                finish();
            }
        });

        patientSignup = (LinearLayout) findViewById(R.id.patientSignup);
        patientSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPatientSignup(v);
            }
        });


        clinicSignup = (LinearLayout) findViewById(R.id.clinicSignup);
        clinicSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClinicSignup(v);
            }
        });
    }


    private void setPatientSignup(View view){
        Intent patientSignup = new Intent(this, PatientSignupActivity.class);
        startActivity(patientSignup);
    }

    private void setClinicSignup(View view) {
//<<<<<<< HEAD
//        Intent clinicSignup = new Intent( this, ClinicSignupActivity.class);
//=======
        Intent clinicSignup = new Intent(this, ClinicSignupActivity.class);
//>>>>>>> d4f5615d54d7f68ac02e198e74daacf6c772a796
        startActivity(clinicSignup);
    }

}
