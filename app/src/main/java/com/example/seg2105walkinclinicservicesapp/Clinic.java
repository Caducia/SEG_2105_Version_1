package com.example.seg2105walkinclinicservicesapp;

import java.util.ArrayList;

public class Clinic {

    private String clinicIDName;
    private String clinicName;
    private String clinicEmail;
    private String clinicPhone;
    private String clinicPassword;
    private ArrayList<Service> servicesOffered;

    public Clinic(){}

    public Clinic(String clinicIDName, String clinicName, String clinicEmail, String clinicPhone, String clinicPassword){
        this.clinicIDName = clinicIDName;
        this.clinicName = clinicName;
        this.clinicEmail = clinicEmail;
        this.clinicPhone = clinicPhone;
        this.clinicPassword = clinicPassword;
    }

    public Clinic(String clinicIDName, String clinicName, String clinicEmail, String clinicPhone, String clinicPassword, ArrayList<Service> servicesOffered ){
        this.clinicIDName = clinicIDName;
        this.clinicName = clinicName;
        this.clinicEmail = clinicEmail;
        this.clinicPhone = clinicPhone;
        this.clinicPassword = clinicPassword;
    }

    public String getClinicID() {
        return clinicIDName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getClinicEmail() {
        return clinicEmail;
    }

    public String getClinicPhone() {
        return clinicPhone;
    }

    public String getClinicPassword() {
        return clinicPassword;
    }

}
