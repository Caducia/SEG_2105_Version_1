package com.example.seg2105walkinclinicservicesapp.AdminPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.seg2105walkinclinicservicesapp.R;
import com.example.seg2105walkinclinicservicesapp.Service;
import com.example.seg2105walkinclinicservicesapp.SupportFiles.AppContextHolder;
import com.example.seg2105walkinclinicservicesapp.ViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListServicesAdmin extends AppCompatActivity {

    private String email;
    private String password;
    private RecyclerView serviceList;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference rDatabase;
    private ArrayList<Service> services = new ArrayList<Service>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_services);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        email = intent.getExtras().getString("email");
        password = intent.getExtras().getString("password");

        serviceList = findViewById(R.id.serviceList);

        rDatabase = mDatabase.getReference("Services");
        Query query = rDatabase.orderByValue();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);
                    services.add(service);
                    Toast.makeText(ListServicesAdmin.this, service.getName(), Toast.LENGTH_SHORT).show();

                }

                ServiceAdapterAdmin adapter = new ServiceAdapterAdmin(services);
                // Attach the adapter to the recyclerview to populate items
                serviceList.setAdapter(adapter);
                // Set layout manager to position the items
                serviceList.setLayoutManager(new LinearLayoutManager(ListServicesAdmin.this));





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListServicesAdmin.this, "Could note connect properly to server", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void startUpdating(View v){
        ViewHolder holder = new ViewHolder(v);
        Intent loginIntent = new Intent(ListServicesAdmin.this, UpdateServiceAdminPage.class);
        loginIntent.putExtra("name", holder.nameTextView.getText().toString());
        loginIntent.putExtra("provider" , holder.providerTextView.getText().toString());
        v.getContext().startActivity(loginIntent);

    }

}
