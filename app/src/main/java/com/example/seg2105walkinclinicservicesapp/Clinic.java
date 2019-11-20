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

    public Timetable getTimetable() {
        return availability;
    }

    public void setOpenHours(int weekDay, int firstSlot, int lastSlot, boolean available, boolean resetHours) {
        Timetable.Week newSampleWeek = availability.getClinicOpenHours();

        if (resetHours || newSampleWeek == null) {
            newSampleWeek = availability.new Week(0);
        }

        if (!available) {
            newSampleWeek.reserveSlots(weekDay, firstSlot, lastSlot, "CLOSED");
        }
        else {
            newSampleWeek.freeUpSlots(weekDay, firstSlot, lastSlot);
        }

        availability.setWorkingHours(newSampleWeek);
    }

    public Timetable.Week getOpenHours() {
        return availability.getClinicOpenHours();
    }
}
