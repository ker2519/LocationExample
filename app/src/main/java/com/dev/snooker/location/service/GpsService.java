package com.dev.snooker.location.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.dev.snooker.location.model.MyLocaiton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Snooker on 2/1/2018 AD.
 */

public class GpsService extends Service implements LocationListener {

    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1 * 60 * 1000;
    private static final float LOCATION_DISTANCE = 10f;

    LocationListener mLocationListener = this;
    Location mLastLocation;
    // Database
    private DatabaseReference mDatabase;

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }

        try {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    LOCATION_INTERVAL, LOCATION_DISTANCE, mLocationListener);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }


    }


    @Override
    public void onCreate() {
        super.onCreate();
        initializeLocationManager();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(mLocationListener);
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, "onLocationChanged: " + location);
        long unixTime = System.currentTimeMillis() / 1000L;
        mDatabase.child("Location").child(String.format("%d", unixTime)).setValue(new MyLocaiton(location.getLongitude() , location.getLatitude() , 0 ,unixTime));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.e(TAG, "onStatusChanged: " + s);

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.e(TAG, "onProviderEnabled: " + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.e(TAG, "onProviderDisabled: " + s);
    }


}
