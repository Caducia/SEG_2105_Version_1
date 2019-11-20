package com.example.seg2105walkinclinicservicesapp.AdminPages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.seg2105walkinclinicservicesapp.R;
import com.example.seg2105walkinclinicservicesapp.Service;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views

public class ServiceAdapter extends
        RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Service> services;

    // Pass in the contact array into the constructor
    public ServiceAdapter(List<Service> services) {
        this.services = services;
    }

    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View serviceView = inflater.inflate(R.layout.better_service_entry, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(serviceView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ServiceAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Service serviceHolder = services.get(position);

        // Set item views based on your views and data model
        TextView serviceView = viewHolder.nameTextView;
        serviceView.setText(serviceHolder.getName());

        TextView providerView = viewHolder.providerTextView;
        providerView.setText(serviceHolder.getProvider());

        Spinner staffSpinner = viewHolder.staffType;
        providerView.setText(serviceHolder.getProvider());


        CheckBox checkBox = viewHolder.selectedService;
        checkBox.setSelected(false);
//        button.setText(contact.isOnline() ? "Message" : "Offline");
//        button.setEnabled(serviceHolder.isOnline());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return services.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView providerTextView;
        public Spinner staffType;
        public CheckBox selectedService;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.service_name_field);
            providerTextView = (TextView) itemView.findViewById(R.id.rate_text_view);
            staffType = (Spinner) itemView.findViewById(R.id.staffSpinner);
            selectedService = (CheckBox) itemView.findViewById(R.id.selectedCheck);

        }


    }




}