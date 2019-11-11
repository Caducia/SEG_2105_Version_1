package com.example.seg2105walkinclinicservicesapp;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public TextView nameTextView;
    public TextView providerTextView;
    public CheckBox selectedService;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);

        nameTextView = (TextView) itemView.findViewById(R.id.service_name);
        providerTextView = (TextView) itemView.findViewById(R.id.provider_text);
        selectedService = (CheckBox) itemView.findViewById(R.id.selected);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    if(selectedService.isSelected()){
//                        selectedService.setSelected(false);
//                    }else{
//                        selectedService.setSelected(true);
//                    }



            }
        });
    }


}
