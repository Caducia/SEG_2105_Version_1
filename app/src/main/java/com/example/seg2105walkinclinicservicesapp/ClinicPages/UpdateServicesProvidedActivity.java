package com.example.seg2105walkinclinicservicesapp.ClinicPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.seg2105walkinclinicservicesapp.AdminPages.ServiceAdapter;
import com.example.seg2105walkinclinicservicesapp.AdminPages.UpdateServiceAdminPage;
import com.example.seg2105walkinclinicservicesapp.AdminPages.UpdateUsersAdminPage;
import com.example.seg2105walkinclinicservicesapp.Clinic;
import com.example.seg2105walkinclinicservicesapp.Patient;
import com.example.seg2105walkinclinicservicesapp.R;
import com.example.seg2105walkinclinicservicesapp.Service;
import com.example.seg2105walkinclinicservicesapp.SupportFiles.RowEntry;
import com.example.seg2105walkinclinicservicesapp.ViewHolder;
import com.example.seg2105walkinclinicservicesapp.WelcomeScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateServicesProvidedActivity extends AppCompatActivity {

    private String email;
    private String clinicID;
    private ListView serviceList;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference rDatabase;
    private ArrayList<Service> providedServices = new ArrayList<Service>();
    private ArrayList<Service> nonProvidedServices = new ArrayList<Service>();
    private Switch serviceSwitch;
    public boolean subscribed = false;
    private Context context;
    public int size = 0;
    public int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_services_provided);

        context = getApplicationContext();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        email = intent.getExtras().getString("email");
        clinicID = intent.getExtras().getString("clinicID");

        serviceList = findViewById(R.id.serviceList);
//
        serviceSwitch = findViewById(R.id.serviceTypeSwitch);
        serviceSwitch.setText("Provided");


        loadUnSubscribedServices();
        serviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if(isChecked){
                      loadUnSubscribedServices();
                      serviceSwitch.setText("Provided");
                      subscribed = true;
                  }else{
                      loadUnSubscribedServices();
                      serviceSwitch.setText("Available");
                      subscribed = false;
                  }
              }
          }
        );


        try{

            if(subscribed){
                loadUnSubscribedServices();
                ArrayList<Service> holder = (ArrayList<Service>) nonProvidedServices.clone();

            }else{
                loadUnSubscribedServices();
                ArrayList<Service> holder = (ArrayList<Service>) nonProvidedServices.clone();
            }

            Toast.makeText(UpdateServicesProvidedActivity.this, "w", Toast.LENGTH_SHORT).show();

            for(int i = 0; i < size; i++){
                    Toast.makeText(UpdateServicesProvidedActivity.this, "what", Toast.LENGTH_LONG).show();
                    pos = i;
//                    serviceList.getLayoutManager().findViewByPosition(i).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(UpdateServicesProvidedActivity.this, "hello", Toast.LENGTH_SHORT).show();
//                            int position = pos;
////                    Intent loginIntent = new Intent(UpdateServicesProvidedActivity.this, UpdateServicesProvidedActivity.class);
//                            String serviceName = "";
//                            String provider = "";
//                            if(!subscribed){
//                                serviceName = providedServices.get(position).getName();
//                                provider = providedServices.get(position).getName();
//
//                                rDatabase = mDatabase.getReference("Services");
//                                Query query = rDatabase.orderByKey().equalTo(serviceName);
//                                query.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        Service service;
//                                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
//                                            service = snapshot.getValue(Service.class);
//                                            Toast.makeText(UpdateServicesProvidedActivity.this, "hello", Toast.LENGTH_SHORT).show();
//                                            service.addSubscription(clinicID,10);
//                                            rDatabase.child(service.getName()).setValue(service);
//                                            providedServices.add(service);
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//
//                                });
//                            }else{
//                                position = pos;
//                                serviceName = nonProvidedServices.get(position).getName();
//                                provider = nonProvidedServices.get(position).getName();
//
//                                rDatabase = mDatabase.getReference("Services");
//                                Query query = rDatabase.orderByKey().equalTo(serviceName);
//                                query.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        Service service;
//                                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
//                                            service = snapshot.getValue(Service.class);
//                                            try{
//                                                service.removeSubscription(clinicID);
//                                            }catch (Exception e){
//
//                                            }
//
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//
//                                });
//                            }
//
//                        }
//                    });
                }

//            for(int i = 0; i < serviceList.getLayoutManager().getChildCount(); i++){

//            }
        }catch(Exception e){

        }








    }

    public void startUpdating(View v){
//        ViewHolder holder = new ViewHolder(v);
//        Intent loginIntent = new Intent(UpdateServicesProvidedActivity.this, .class);
//        loginIntent.putExtra("name", holder.nameTextView.getText().toString());
//        loginIntent.putExtra("provider" , holder.providerTextView.getText().toString());
//        v.getContext().startActivity(loginIntent);

    }
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
    public void loadUnSubscribedServices(){
//        userList.removeAllViewsInLayout();
        nonProvidedServices.clear();

//        try{
//            ServiceAdapter holder = (ServiceAdapter) serviceList.getAdapter();
//            holder.clear();
//            holder.notifyDataSetChanged();
//        }catch(Exception e){
//
//        }
        rDatabase = mDatabase.getReference("Services");
        Query query = rDatabase.orderByValue();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Service[] servicesHolder;
                nonProvidedServices = new ArrayList<Service>();
                long size = dataSnapshot.getChildrenCount();
                servicesHolder = new Service[(int) size];
                int increment = -1;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);
                    increment++;
                    try {
                        if(!(service.getSubscriptions().containsKey(clinicID))){
                            nonProvidedServices.add(service);
                            servicesHolder[increment] = service;
                        }
                    }catch(Exception e){
                        nonProvidedServices.add(service);
                        servicesHolder[increment] = new Service(service.getName(),service.getProvider());
                    }

                }

                String[] values = new String[2];
                String[] options = {"Staff", "Nurses", "Doctors"};

                for (int i = 0; i < servicesHolder.length; i++){
                    values[0] = servicesHolder[i].getName();
                    values[1] = "$";
                    RowEntry serviceEntry = new RowEntry(context,values, options);
                    serviceList.addView(serviceEntry);
                }



//                servicesHolder = (Service[]) nonProvidedServices.toArray();





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateServicesProvidedActivity.this, "Could note connect properly to server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadSubscribedServices(){
//        userList.removeAllViewsInLayout();
//        providedServices.clear();
        rDatabase = mDatabase.getReference("Services");
        Query query = rDatabase.orderByValue();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);

                    try {
                        if((service.getSubscriptions().containsKey(clinicID))){
                            providedServices.add(service);
                        }
                    }catch(Exception e){

                    }


                }



//                ServiceAdapter adapter = new ServiceAdapter(providedServices,clinicID);
//                // Attach the adapter to the recyclerview to populate items
//                serviceList.setAdapter(adapter);
//                // Set layout manager to position the items
//                serviceList.setLayoutManager(new LinearLayoutManager(UpdateServicesProvidedActivity.this));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateServicesProvidedActivity.this, "Could note connect properly to server", Toast.LENGTH_LONG).show();
            }
        });
    }

}
