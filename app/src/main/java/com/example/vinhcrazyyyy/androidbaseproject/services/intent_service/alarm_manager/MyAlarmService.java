package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.alarm_manager;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/*Using AlarmManager for Periodic Tasks
* Suppose we need to set periodically executing background tasks.
 * For example, we want to be able to check for new emails or
 * content from a server every 15 minutes even if our application isn't running.
 * This is useful for apps like email clients, news renders, instant messaging clients, et al.
 * In this case, we don't necessarily need a long running task that runs forever.
 * That would take drain battery life significantly and isn't what we want anyways.
 *
 * For most of these common cases (checking for new data), what we really want to do
 * is setup a "scheduler that triggers a background service at a regular interval"
 * of our choosing. The best way to achieve this is to use an IntentService in conjunction
 * with the AlarmManager. First, we want to define the IntentService to
 * have periodically execute*/
//1. Create the Alarm Service
public class MyAlarmService extends IntentService {

    public MyAlarmService() {
        super("MyAlarmService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyAlarmService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Do the task here
        Log.i("MyAlarmService", "Service running ");
    }
    /*Now we want to setup a way to execute this periodically at a specified interval.
    * Enter the AlarmManager to execute a periodic task by firing a BroadcastIntent.
    * First though, let's define the BroadcastReceiver that will be executed
    * by the alarm and will launch our IntentService*/
}
