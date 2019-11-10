package com.example.seg2105walkinclinicservicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminPage extends AppCompatActivity {

    public String email;
    public String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        Intent intent = getIntent();

        email = intent.getExtras().getString("email");
        password = intent.getExtras().getString("password");
    }

    public void addService(View v){
        Intent addService = new Intent(AdminPage.this, addServiceAdminPage.class);
        addService.putExtra("email" , email);
        addService.putExtra("password" , password);
        startActivity(addService);
    }

    public void editServices(View v){
        Intent addService = new Intent(AdminPage.this, EditServices.class);
        addService.putExtra("email" , email);
        addService.putExtra("password" , password);
        startActivity(addService);
    }
}
