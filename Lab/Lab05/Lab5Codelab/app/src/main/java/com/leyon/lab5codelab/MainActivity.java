package com.leyon.lab5codelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private HeadsetPlugBroadcastReceiver headsetPlugBroadcastReceiver = new HeadsetPlugBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        //filter.addAction(Intent.ACTION_BATTERY_LOW);
        this.registerReceiver(headsetPlugBroadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(headsetPlugBroadcastReceiver);

        super.onDestroy();
    }
}