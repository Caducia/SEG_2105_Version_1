package com.example.seg2105walkinclinicservicesapp;

public class Patient {
    private String firstName;
    private String lastName;
    private String email;
    private String studentNo;
    private String password;
    private String phoneNumber;

    public Patient(){}

    public Patient(String firstName, String lastName, String studentNo, String email, String password, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNo = studentNo;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public String getPassword() {
        return password;
    }
}
