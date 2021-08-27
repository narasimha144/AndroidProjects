package com.example.assign4app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static final String MESSAGE = "com.example.assign4.MESSAGE";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra(MESSAGE), Toast.LENGTH_SHORT).show();
    }
}
