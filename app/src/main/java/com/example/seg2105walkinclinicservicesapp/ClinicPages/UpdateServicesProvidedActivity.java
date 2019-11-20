//package com.example.seg2105walkinclinicservicesapp.ClinicPages;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.CompoundButton;
//import android.widget.Switch;
//import android.widget.Toast;
//
//import com.example.seg2105walkinclinicservicesapp.AdminPages.ServiceAdapter;
//import com.example.seg2105walkinclinicservicesapp.Clinic;
//import com.example.seg2105walkinclinicservicesapp.Patient;
//import com.example.seg2105walkinclinicservicesapp.R;
//import com.example.seg2105walkinclinicservicesapp.Service;
//import com.example.seg2105walkinclinicservicesapp.ViewHolder;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class UpdateServicesProvidedActivity extends AppCompatActivity {
//
//    private String email;
//    private String password;
//    private RecyclerView userList;
//    private FirebaseAuth mAuth;
//    private FirebaseDatabase mDatabase;
//    private DatabaseReference rDatabase;
//    private ArrayList<Clinic> clinics = new ArrayList<Clinic>();
//    private ArrayList<Patient> patients= new ArrayList<Patient>();
//    private Switch userSwitch;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_users_admin_page);
//
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance();
//
//        Intent intent = getIntent();
//        email = intent.getExtras().getString("email");
//        password = intent.getExtras().getString("password");
//
//        userList = findViewById(R.id.userList);
//
//        userSwitch = findViewById(R.id.userTypeSwitch);
//        loadPatients();
//        userSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//              @Override
//              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                  if(isChecked){
//                      loadClinics();
//                      userSwitch.setText("Clinics");
//                  }else{
//                      loadPatients();
//                      userSwitch.setText("Patients");
//                  }
//              }
//          }
//        );
//
//
//
//
//
//
//
//    }
//
//    public void startUpdating(View v){
////        ViewHolder holder = new ViewHolder(v);
////        Intent loginIntent = new Intent(UpdateServicesProvidedActivity.this, .class);
////        loginIntent.putExtra("name", holder.nameTextView.getText().toString());
////        loginIntent.putExtra("provider" , holder.providerTextView.getText().toString());
////        v.getContext().startActivity(loginIntent);
//
//    }
//
//    public void loadClinics(){
////        userList.removeAllViewsInLayout();
//        clinics.clear();
//        rDatabase = mDatabase.getReference("ClinicEmployees");
//        Query query = rDatabase.orderByValue();
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
//                    Clinic clinic = snapshot.getValue(Clinic.class);
//                    clinics.add(clinic);
////                    Toast.makeText(ShowUsersAdminPage.this, clinic.getClinicName(), Toast.LENGTH_SHORT).show();
//
//                }
//
//                ClinicAdapter adapter = new ClinicAdapter(clinics);
//                // Attach the adapter to the recyclerview to populate items
//                userList.setAdapter(adapter);
//                // Set layout manager to position the items
//                userList.setLayoutManager(new LinearLayoutManager(ShowUsersAdminPage.this));
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ShowUsersAdminPage.this, "Could note connect properly to server", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    public void loadPatients(){
////        userList.removeAllViewsInLayout();
//        patients.clear();
//        rDatabase = mDatabase.getReference("Services");
//        Query query = rDatabase.orderByValue();
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
//                    Service patient = snapshot.getValue(Patient.class);
//                    patients.add(patient);
//                    Toast.makeText(UpdateServicesProvidedActivity.this, patient.getFirstName(), Toast.LENGTH_SHORT).show();
//
//                }
//
//                ServiceAdapter adapter = new ServiceAdapter(patients);
//                // Attach the adapter to the recyclerview to populate items
//                userList.setAdapter(adapter);
//                // Set layout manager to position the items
//                userList.setLayoutManager(new LinearLayoutManager(ShowUsersAdminPage.this));
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ShowUsersAdminPage.this, "Could note connect properly to server", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//}
