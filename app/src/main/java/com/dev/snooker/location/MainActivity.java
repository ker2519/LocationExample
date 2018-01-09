package com.dev.snooker.location;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dev.snooker.location.service.GpsService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int MY_PERMISSIONS_REQUEST = 1001;
    private Button btnStart, btnStop, btnPage2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnPage2 = (Button) findViewById(R.id.btn_page2);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPage2.setOnClickListener(this);


        checkUI();
    }

    private void startLocationService() {
        if (checkSelfPermission()) {
            Intent intent = new Intent(this, GpsService.class);
            startService(intent);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST);
        }
    }

    private void stopLocationService() {
        Intent intent = new Intent(getApplicationContext(), GpsService.class);
        stopService(intent);
    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    private boolean checkSelfPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false;
        }

        return true;
    }

    private void checkUI() {
        if (isMyServiceRunning(GpsService.class)) {
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        } else {
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    startLocationService();
                    checkUI();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            default:
                return;

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start) {
            startLocationService();
            checkUI();
        } else if (view.getId() == R.id.btn_stop) {
            stopLocationService();
            checkUI();
        } else if (view.getId() == R.id.btn_page2) {
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
        }
    }
}
