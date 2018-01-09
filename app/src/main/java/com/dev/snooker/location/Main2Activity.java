package com.dev.snooker.location;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText txUsername , txPassword;
    Button btnSignin;

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

        btnSignin.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_signin){
            String username = txUsername.getText().toString();
            String password = txPassword.getText().toString();
            Log.e("Username ", "onClick: " + username);
            Log.e("Password ", "onClick: " + password);
        }

    }
}
