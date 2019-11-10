package com.example.seg2105walkinclinicservicesapp;

public class Account {
    String accountID;
    String firstName;
    String lastName;
    String emailAddress;
    String phoneNumber;
    boolean isAdmin;

    public Account(String accountID, String firstName, String lastName, String emailAddress, String phoneNumber, boolean isAdmin) {
        this.accountID = accountID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getAccountID() {
        return this.accountID;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
