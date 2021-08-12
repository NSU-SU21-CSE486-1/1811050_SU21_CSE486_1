package com.leyon.lab5codelab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class HeadsetPlugBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction.equals(Intent.ACTION_HEADSET_PLUG)) {
            Toast.makeText(context, "ACTION_HEADSET_PLUG broadcast received", Toast.LENGTH_LONG).show();
        }

        //if (intentAction.equals(Intent.ACTION_BATTERY_LOW)) {
        //    Toast.makeText(context, "ACTION_BATTERY_LOW broadcast received", Toast.LENGTH_LONG).show();
        //}
    }
}