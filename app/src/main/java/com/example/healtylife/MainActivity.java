package com.example.healtylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import javax.net.ssl.HostnameVerifier;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=7000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomeIntent=new Intent(MainActivity.this,loginActivity.class);
                startActivity(HomeIntent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }
}