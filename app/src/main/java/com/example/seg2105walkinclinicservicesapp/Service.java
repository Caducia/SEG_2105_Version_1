package com.example.seg2105walkinclinicservicesapp;

public class Service {
    private String name;
    private String provider;

    public Service(){}

    public Service(String name, String provider){
        this.name = name;
        this.provider = provider;
    }

    public String getProvider() { return provider; }

    public String getName() {
        return name;
    }

}
