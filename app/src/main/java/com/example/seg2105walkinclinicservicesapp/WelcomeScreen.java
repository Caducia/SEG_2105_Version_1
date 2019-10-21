package com.example.seg2105walkinclinicservicesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseUser user;
    private DatabaseReference rDatabase;
    private String uID;
    private String email;
    private String password;
//    private String message = getString(R.string.welcome_text);
    private Clinic clinic;
    private Patient patient;

    private TextView welcomeText;
    private TextView userType;
    private TextView fullName;
    private TextView phoneNumber;
    private TextView emailView;
    private TextView uniqueId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        email = intent.getExtras().getString("email");
        password = intent.getExtras().getString("password");

        userType = findViewById(R.id.userType);
        fullName = findViewById(R.id.fullName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailView = findViewById(R.id.email);
        uniqueId = findViewById(R.id.uniqueId);

        //If user is already logged in
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            user = mAuth.getCurrentUser();
                            Toast.makeText(WelcomeScreen.this, "Signed In.", Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();
                            uID = user.getUid();
                            email = user.getEmail();



                            try{
                                rDatabase = mDatabase.getReference("Students");
                                Query query = rDatabase.orderByKey().equalTo(uID);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                            if(snapshot.getKey().equals(uID)){
                                                Patient patient = snapshot.getValue(Patient.class);
                                                welcomeText = (TextView) findViewById(R.id.welcomeTextView);
                                                String finalMessage = getString(R.string.welcome_text) + patient.getFirstName();
                                                welcomeText.setText(finalMessage);
                                                userType.setText("Patient");
                                                fullName.setText("Name : " + patient.getFirstName() + patient.getLastName());
                                                uniqueId.setText("Number : " + patient.getStudentNo());
                                                emailView.setText("Email : " + patient.getEmail());
                                                phoneNumber.setText("Phone Number : "  + patient.getPhoneNumber());

                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                rDatabase = mDatabase.getReference("ClinicEmployees");
                                query = rDatabase.orderByKey().equalTo(uID);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                            if(snapshot.getKey().equals(uID)){
                                                Clinic clinic = snapshot.getValue(Clinic.class);
                                                welcomeText = (TextView) findViewById(R.id.welcomeTextView);
                                                String finalMessage = getString(R.string.welcome_text) + clinic.getClinicName();
                                                welcomeText.setText(finalMessage);
                                                userType.setText("Clinic");
                                                fullName.setText("Name : " + clinic.getClinicName() );
                                                uniqueId.setText("Number : " + clinic.getClinicID());
                                                emailView.setText("Email : " + clinic.getClinicEmail());
                                                phoneNumber.setText("Phone Number : "  + clinic.getClinicPhone());
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }catch(Exception e){

                            }



                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(WelcomeScreen.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                    }


                });









    }
}
