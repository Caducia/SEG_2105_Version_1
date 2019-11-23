package com.example.seg2105walkinclinicservicesapp.SupportFiles;


import android.app.Application;
import android.content.Context;

public class AppContextHolder extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        AppContextHolder.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AppContextHolder.context;
    }
}

