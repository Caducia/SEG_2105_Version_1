package com.example.seg2105walkinclinicservicesapp;

public class Service {
    private String name;
    private String provider;
    private int price;

    public Service(){}

    public Service(String name, String provider){
        this.name = name;
        this.provider = provider;
    }

    public Service(String name, String provider, int price){
        this.name = name;
        this.provider = provider;
        this.price = price;
    }

    public String getProvider() { return provider; }

    public String getName() {
        return name;
    }

}
