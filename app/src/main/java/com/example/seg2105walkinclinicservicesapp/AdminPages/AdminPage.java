package com.example.seg2105walkinclinicservicesapp.AdminPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seg2105walkinclinicservicesapp.R;

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
        Intent editService = new Intent(AdminPage.this, ListServicesAdmin.class);
        editService.putExtra("email" , email);
        editService.putExtra("password" , password);
        startActivity(editService);
    }

    public void editUsers(View v){
        Intent editUser = new Intent(AdminPage.this, ShowUsersAdminPage.class);
        editUser.putExtra("email" , email);
        editUser.putExtra("password" , password);
        startActivity(editUser);
    }

}
