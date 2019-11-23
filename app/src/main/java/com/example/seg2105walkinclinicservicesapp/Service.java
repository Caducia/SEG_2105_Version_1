package com.example.seg2105walkinclinicservicesapp;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Service {
    private String name;
    private String provider;
    private int price;
    private Map<String, Integer> subscriptions;


    public Service(){}

    public Service(String name, String provider){
        this(name, provider, 0);
    }

    public Service(String name, String provider, int price){
        this.name = name;
        this.provider = provider;
        this.price = price;
        subscriptions = new HashMap<>(20);

    }

    public String getProvider() { return provider; }

    public String getName() {
        return name;
    }


    public Map<String, Integer> getSubscriptions() {
        return subscriptions;
    }

    public void addSubscription(String clinicID, Integer rate){
        subscriptions = new HashMap<String, Integer>();
        this.subscriptions.put(clinicID, rate);
    }

    public boolean removeSubscription(String clinicID){
        return this.subscriptions.remove(clinicID, this.subscriptions.get(clinicID));
    }
}
