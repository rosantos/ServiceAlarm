package com.example.rosantos.servicealarm.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public class AlarmBroadcastReceiver extends BroadcastReceiver{
    public static final String ACTION_ALARM = "com.example.rosantos.servicealarm.service.action.ACTION_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TAG");

        // acquire the lock
        wl.acquire();
        int interval = intent.getIntExtra("INTERVAL", 15);

        MyIntentService.startService(context, interval);

        wl.release();
    }
}
