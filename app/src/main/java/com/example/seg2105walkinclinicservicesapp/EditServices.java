package com.example.seg2105walkinclinicservicesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditServices extends AppCompatActivity {

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
                    Toast.makeText(EditServices.this, service.getName(), Toast.LENGTH_SHORT).show();

                }
                String[] values = new String[services.size()];
                for(int i = 0; i < services.size(); i++){
                    Service holder = services.get(i);
                    String val = holder.getName() + " : " + holder.getProvider();
                    values[i] = val;
                }

//                MyAdapter adapter = new MyAdapter(values);
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditServices.this,android.R.layout.simple_expandable_list_item_2, values);
//                ArrayAdapter<>

//                serviceList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditServices.this, "Could note connect properly to server", Toast.LENGTH_LONG).show();
            }
        });


    }

}
