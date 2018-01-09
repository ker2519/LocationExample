package com.dev.snooker.location;

import android.app.Application;
import android.content.res.Configuration;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Snooker on 2/1/2018 AD.
 */
public class LocationApplication extends Application {

    // private FirebaseDatabase database;
    // private DatabaseReference locationDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        //   database = FirebaseDatabase.getInstance();
        //  locationDatabase = database.getReference("location");

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    // public DatabaseReference getLocationDatabase() {
    //   return locationDatabase;
    // }
}
