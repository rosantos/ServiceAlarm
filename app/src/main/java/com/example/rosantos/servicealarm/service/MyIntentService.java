package com.example.rosantos.servicealarm.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import org.androidannotations.annotations.EService;

import java.util.Calendar;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
@EService
public class MyIntentService extends IntentService {

    private static final String ACTION_TIMER = "com.example.rosantos.servicealarm.service.action.TIMER";

    private static final String EXTRA_PARAM1 = "com.example.rosantos.servicealarm.service.extra.TIMER_SECONDS";

    private static PendingIntent alarmIntent;

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startService(Context context, Integer interval) {
        Intent intent = new Intent(context, MyIntentService_.class);
        intent.setAction(ACTION_TIMER);
        intent.putExtra(EXTRA_PARAM1, interval);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_TIMER.equals(action)) {
                final Integer param1 = intent.getIntExtra(EXTRA_PARAM1, 15);
                handleExecuteTimer(param1);
                reschedule(param1);
            }
        }
    }

    private void reschedule(Integer interval) {
        Log.i(getClass().getName(),"Start reschedule: "+interval);
        Intent intentToFire = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
        intentToFire.setAction(AlarmBroadcastReceiver.ACTION_ALARM);
        intentToFire.putExtra("INTERVAL", interval);

        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(),
                0, intentToFire, 0);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, interval);

        AlarmManager alarmManager = (AlarmManager)
                this.getSystemService(this.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= 23)
        {
            Log.i(getClass().getName(), "Alarm set. >= 23: "+cal.getTime());
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmIntent);
        }
        else if (Build.VERSION.SDK_INT >= 19)
        {
            Log.i(getClass().getName(), "Alarm set. >= 19: "+cal.getTime());
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,  cal.getTimeInMillis(), alarmIntent);
        }
        else
        {
            Log.i(getClass().getName(), "Alarm set. < 19: "+cal.getTime());
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmIntent);
        }
    }

    private void handleExecuteTimer(Integer param1) {
        Log.i(getClass().getName(),"Start Execution: "+param1);
    }

    public static void stopAlarm(Context context){
        Log.i(MyIntentService.class.getName(), "Stop Service");
        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(context.ALARM_SERVICE);
        if (alarmIntent!= null){
            alarmManager.cancel(alarmIntent);
            alarmIntent = null;
        }
    }

}
