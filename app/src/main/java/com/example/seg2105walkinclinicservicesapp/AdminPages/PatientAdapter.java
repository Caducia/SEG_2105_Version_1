package com.example.seg2105walkinclinicservicesapp.AdminPages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.seg2105walkinclinicservicesapp.Clinic;
import com.example.seg2105walkinclinicservicesapp.Patient;
import com.example.seg2105walkinclinicservicesapp.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views

public class PatientAdapter extends
        RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Patient> patients;
    private Context context;

    // Pass in the contact array into the constructor
    public PatientAdapter(List<Patient> patient) {
        this.patients = patient;
    }

    @Override
    public PatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View serviceView = inflater.inflate(R.layout.better_service_entry, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(serviceView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Patient serviceHolder = patients.get(position);

        // Set item views based on your views and data model
        TextView serviceView = viewHolder.nameTextView;
        serviceView.setText(serviceHolder.getFirstName());

        TextView providerView = viewHolder.studentNumberTextView;
        providerView.setText(serviceHolder.getStudentNo());

        CheckBox checkBox = viewHolder.selected;
        checkBox.setSelected(false);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(context, UpdateUsersAdminPage.class);
//                loginIntent.putExtra("service" , viewHolder.nameTextView.getText().toString());
//                loginIntent.putExtra("provider" , viewHolder.studentNumberTextView.getText().toString());
                context.startActivity(loginIntent);
            }
        });
//        button.setText(contact.isOnline() ? "Message" : "Offline");
//        button.setEnabled(serviceHolder.isOnline());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return patients.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView studentNumberTextView;
        public Spinner paymentsAvailable;
        public CheckBox selected;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.service_name_field);
            studentNumberTextView = (TextView) itemView.findViewById(R.id.rate_text_view);
            paymentsAvailable = (Spinner) itemView.findViewById((R.id.staffSpinner));
            selected = (CheckBox) itemView.findViewById(R.id.selectedCheck);

        }


    }




}