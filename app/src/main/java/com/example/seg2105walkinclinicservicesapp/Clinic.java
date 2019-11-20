package com.example.seg2105walkinclinicservicesapp;

import java.sql.Time;

public class Clinic {

    private String clinicIDName;
    private String clinicName;
    private String clinicEmail;
    private String clinicPhone;
    private String clinicPassword;

    private Timetable availability;

    public Clinic(){}

    public Clinic(String clinicIDName, String clinicName, String clinicEmail, String clinicPhone, String clinicPassword){
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

    public Timetable getAvailability() {
        return availability;
    }

    public void setAvailability(int weekDay, boolean available, int firstSlot, int lastSlot) {
        Timetable.Week newSampleWeek = availability.new Week(0);

        if (!available) {
            newSampleWeek.reserveSlots(weekDay, firstSlot, lastSlot, "CLOSED");
        }
        else {
            newSampleWeek.freeUpSlots(weekDay, firstSlot, lastSlot);
        }

        availability.setClinicOpenHours(newSampleWeek);
    }
}
