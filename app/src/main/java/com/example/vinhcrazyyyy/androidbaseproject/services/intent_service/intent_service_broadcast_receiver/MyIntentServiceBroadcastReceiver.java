package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_broadcast_receiver;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

public class MyIntentServiceBroadcastReceiver extends IntentService {

    public static final String ACTION = "com.example.vinhcrazyyyy.androidbaseproject.servies.MyIntentServiceBroadcastReceiver";

    //Must have default constructor
    public MyIntentServiceBroadcastReceiver() {
        super("test-service");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentServiceBroadcastReceiver(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Fetch data passed to the intent on start
        String val = intent.getStringExtra("foo");
        //Construct an Intent tying it to the ACTION (arbitrary event namespace)
        Intent in = new Intent(ACTION);
        //Put extras into the intent as usual
        in.putExtra("resultCode", Activity.RESULT_OK);
        in.putExtra("resultValue", "My Result Value. Passed in: " + val);
        //Fire the broadcast with intent packaged
        LocalBroadcastManager.getInstance(this).sendBroadcast(in);
        //or sendBroadcast(in) for a normal broadcast
    }
    /*The service is now sending this broadcast message to any application that wants
    * to listen for these messages based on the ACTION namspace.
    * Next, we need to construct a new BroadcastReceiver, register to listen and define
    * the onReceive method to handle the messages within our Activity/Fragment*/
}
