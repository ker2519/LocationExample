package com.dev.snooker.location;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText txUsername, txPassword;
    private Button btnSignin, btnAlarm;
    private static int REQUEST_CODE_ALARM = 10002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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

        txUsername = findViewById(R.id.tx_username);
        txPassword = findViewById(R.id.tx_password);
        btnSignin = findViewById(R.id.btn_signin);
        btnAlarm = findViewById(R.id.btn_alarm);

        btnSignin.setOnClickListener(this);
        btnAlarm.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_signin) {
            String username = txUsername.getText().toString();
            String password = txPassword.getText().toString();
            Log.e("Username ", "onClick: " + username);
            Log.e("Password ", "onClick: " + password);
        } else if (id == R.id.btn_alarm) {
            createAlarm();
        }
    }

    private void createAlarm() {
        Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.SECOND, 5);
        int date =  cal.get(Calendar.DATE);
        int mouth =  cal.get(Calendar.MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE)+1;
        int year = cal.get(Calendar.YEAR);
        cal.set(year,mouth,date,hour,minute);
        //Create a new PendingIntent and add it to the AlarmManager
        Intent intent = new Intent(getApplicationContext(), AlarmReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), REQUEST_CODE_ALARM, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }
}
