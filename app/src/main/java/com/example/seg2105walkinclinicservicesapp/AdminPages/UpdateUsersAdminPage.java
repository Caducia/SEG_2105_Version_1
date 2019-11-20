package com.example.seg2105walkinclinicservicesapp.AdminPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.seg2105walkinclinicservicesapp.R;
import com.example.seg2105walkinclinicservicesapp.Service;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateUsersAdminPage extends AppCompatActivity {

    private String email;
    private String password;

    private FirebaseDatabase mDatabase;
    private DatabaseReference rDatabase;
    private FirebaseAuth mAuth;
    private String directory;

    private TextInputEditText serviceNameInput;
    private Spinner providerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service_admin_page);

        mDatabase = FirebaseDatabase.getInstance();
        directory = "Students";

        Intent intent = getIntent();
        email = intent.getExtras().getString("email");
        password = intent.getExtras().getString("passwors");

        serviceNameInput = findViewById(R.id.serviceRename);
        providerSpinner = findViewById(R.id.serviceProviderLister);

        String[] providers = new String[]{"Doctor", "Nurse", "Staff"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, providers);
//set the spinners adapter to the previously created one.
        providerSpinner.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    // Log.d(TAG, "signInWithEmail:success");
                    // user = mAuth.getCurrentUser();

                    FirebaseUser user = mAuth.getCurrentUser();
                    final String uID = user.getUid();
                    email = user.getEmail();

                    try{
                        rDatabase = mDatabase.getReference(directory);
                        Query query = rDatabase.orderByKey().equalTo(uID);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                                    if(snapshot.getKey().equals(uID)){
//                                        Patient patient = snapshot.getValue(Patient.class);
//                                        welcomeText = (TextView) findViewById(R.id.welcomeTextView);
//                                        String finalMessage = getString(R.string.welcome_text) + patient.getFirstName();
//                                        welcomeText.setText(finalMessage);
//                                        userType.setText("Patient");
//                                        fullName.setText("Name : " + patient.getFirstName() + patient.getLastName());
//                                        uniqueId.setText("Number : " + patient.getStudentNo());
//                                        emailView.setText("Email : " + patient.getEmail());
//                                        phoneNumber.setText("Phone Number : "  + patient.getPhoneNumber());
//
//                                    }

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
//                                        Clinic clinic = snapshot.getValue(Clinic.class);
//                                        welcomeText = (TextView) findViewById(R.id.welcomeTextView);
//                                        String finalMessage = getString(R.string.welcome_text) + clinic.getClinicName();
//                                        welcomeText.setText(finalMessage);
//                                        userType.setText("Clinic");
//                                        fullName.setText("Name : " + clinic.getClinicName() );
//                                        uniqueId.setText("Number : " + clinic.getClinicID());
//                                        emailView.setText("Email : " + clinic.getClinicEmail());
//                                        phoneNumber.setText("Phone Number : "  + clinic.getClinicPhone());
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
                    // Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(UpdateUsersAdminPage.this, "Editing User failed.", Toast.LENGTH_SHORT).show();
                    // updateUI(null);
                }

            }


        });
    }

    public void finishUpdating(View v){
        rDatabase = mDatabase.getReference("Services");
        Query query = rDatabase.orderByKey().equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Service service;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    service = snapshot.getValue(Service.class);
                }

                Service newEntry = new Service(serviceNameInput.getText().toString(),providerSpinner.getSelectedItem().toString());
                mDatabase.getReference("Services/" + email).setValue(newEntry);
                Toast.makeText(UpdateUsersAdminPage.this, "Successfully modified service", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    public void deleteService(View v){
        rDatabase = mDatabase.getReference("Services");
        Query query = rDatabase.orderByKey().equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Service service;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                }
                Toast.makeText(UpdateUsersAdminPage.this, "Successfully deleted service", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

}

