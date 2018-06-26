package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.alarm_manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*Now we want to setup a way to execute this periodically at a specified interval.
 * Enter the AlarmManager to execute a periodic task by firing a BroadcastIntent.
 * First though, let's define the BroadcastReceiver that will be executed
 * by the alarm and will launch our IntentService*/
//2. Create the Alarm Receiver
public class MyAlarmReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "com.example.vinhcrazyyyy.androidbaseproject.services.alarm_manager";

    //Triggered by the Alarm periodically (starts the service to run task)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MyAlarmService.class);
        i.putExtra("foo", "bar");
        context.startService(i);
    }
    /*Now that we have our IntentService task defined and our receiver that will
    * be setup to periodically execute in order to trigger the service.
    * Next, let's register both our IntentService and MyAlarmReceiver
    * in the AndroidManifest.xml*/
}
