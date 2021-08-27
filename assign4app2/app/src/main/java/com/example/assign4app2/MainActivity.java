package com.example.assign4app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiver myReceiver;
    private final String ACTION = "com.example.assign4.SEND_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Receive Broadcast from an external app
        IntentFilter filter = new IntentFilter(ACTION);
        myReceiver = new MyBroadcastReceiver();

        this.registerReceiver(myReceiver, filter);
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}