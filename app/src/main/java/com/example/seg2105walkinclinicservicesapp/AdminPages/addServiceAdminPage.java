package com.example.seg2105walkinclinicservicesapp.AdminPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.seg2105walkinclinicservicesapp.R;
import com.example.seg2105walkinclinicservicesapp.Service;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addServiceAdminPage extends AppCompatActivity {

    private String name;
    private String provider;
    private TextInputEditText serviceNameInput;
    private Spinner serviceProviderSpinner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference rDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_admin_page);

        serviceProviderSpinner = findViewById(R.id.serviceProviderLister);
        serviceNameInput = findViewById(R.id.serviceRename);

        mDatabase = FirebaseDatabase.getInstance();
        rDatabase = mDatabase.getReference("Services");

        String[] providers = new String[]{"Doctor", "Nurse", "Staff"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, providers);
//set the spinners adapter to the previously created one.
        serviceProviderSpinner.setAdapter(adapter);
    }

    public void createService(View v){
        name = serviceNameInput.getText().toString();
        provider = serviceProviderSpinner.getSelectedItem().toString();
        if(name.length() >= 2 && provider != null){
            try{
                Service service = new Service(name, provider);
                rDatabase.child(name).setValue(service);

                Toast.makeText(addServiceAdminPage.this, "Service Successfully Registered", Toast.LENGTH_LONG).show();
                finish();


            }catch(Exception e){
                Toast.makeText(addServiceAdminPage.this, "Make Sure to properly name the service", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(addServiceAdminPage.this, "Service names need to be longer than 2 characters", Toast.LENGTH_LONG).show();
        }
    }
}
