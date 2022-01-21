package com.example.cuchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        Thread thread = new Thread(() -> {
            SystemClock.sleep(5000);
            Intent intent = new Intent(this, Authentication.class);
            startActivity(intent);
            finish();
        });
        thread.start();
    }
}
