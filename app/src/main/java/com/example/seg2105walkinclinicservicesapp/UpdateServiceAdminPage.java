package com.example.seg2105walkinclinicservicesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateServiceAdminPage extends AppCompatActivity {

    private String serviceName;
    private String provider;

    private FirebaseDatabase mDatabase;
    private DatabaseReference rDatabase;

    private TextInputEditText serviceNameInput;
    private Spinner providerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service_admin_page);

        mDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        serviceName = intent.getExtras().getString("name");
        provider = intent.getExtras().getString("provider");

        serviceNameInput = findViewById(R.id.serviceRename);
        providerSpinner = findViewById(R.id.serviceProviderLister);

        String[] providers = new String[]{"Doctor", "Nurse", "Staff"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, providers);
//set the spinners adapter to the previously created one.
        providerSpinner.setAdapter(adapter);
    }

    public void finishUpdating(View v){
        rDatabase = mDatabase.getReference("Services");
        Query query = rDatabase.orderByKey().equalTo(serviceName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Service service;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    service = snapshot.getValue(Service.class);
                }

                Service newEntry = new Service(serviceNameInput.getText().toString(),providerSpinner.getSelectedItem().toString());
                mDatabase.getReference("Services/" + serviceName).setValue(newEntry);
                Toast.makeText(UpdateServiceAdminPage.this, "Successfully modified service", Toast.LENGTH_SHORT).show();
             }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    public void deleteService(View v){
        rDatabase = mDatabase.getReference("Services");
        Query query = rDatabase.orderByKey().equalTo(serviceName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Service service;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                }
                Toast.makeText(UpdateServiceAdminPage.this, "Successfully deleted service", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

}

