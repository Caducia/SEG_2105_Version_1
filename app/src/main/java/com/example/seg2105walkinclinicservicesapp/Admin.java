package com.example.seg2105walkinclinicservicesapp;

public class Admin {
    private String employeeID;
    private String employeeName;
    private String email;
    private String phoneNumber;
    private String adminPassword;

    public Admin(){}

    public Admin(String employeeID, String employeeName, String email, String phoneNumber, String adminPassword){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adminPassword = adminPassword;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getaAminPassword() {
        return adminPassword;
    }


}
