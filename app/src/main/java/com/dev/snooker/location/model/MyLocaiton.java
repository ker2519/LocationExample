package com.dev.snooker.location.model;

/**
 * Created by Snooker on 2/1/2018 AD.
 */

public class MyLocaiton {

    private double Longitude;
    private double Latitude;
    private int UserId;
    private long TimeStemp;

    public MyLocaiton(double longitude, double latitude, int userId, long timeStemp) {
        Longitude = longitude;
        Latitude = latitude;
        UserId = userId;
        TimeStemp = timeStemp;
    }

    public MyLocaiton() {
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public long getTimeStemp() {
        return TimeStemp;
    }

    public void setTimeStemp(long timeStemp) {
        TimeStemp = timeStemp;
    }
}
