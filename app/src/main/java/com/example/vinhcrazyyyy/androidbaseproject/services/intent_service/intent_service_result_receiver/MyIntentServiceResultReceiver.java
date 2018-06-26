package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_result_receiver;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

/*1. Creating an IntentService
* First, you define a class within your application that extends IntentService and defines
* the onHandleIntent which describes the work to do when this intent is executed
* */
public class MyIntentServiceResultReceiver extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentServiceResultReceiver(String name) {
        super("test-service");
    }

    //Must create a default constructor
    public MyIntentServiceResultReceiver() {
        super("test-service");
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super()
        //If a Context object is needed, call getApplicationContext() here
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //This describes what will happen when service is triggered

        //Extract the receiver passed into the service
        ResultReceiver rec = intent.getParcelableExtra("receiver");
        //Extract additional values from the bundle
        String val = intent.getStringExtra("foo");

        //To send a message to the Activity, create and pass a Bundle
        Bundle bundle = new Bundle();
        bundle.putString("resultValue", "My Result Value. Passed in: " + val);
        //Here we call send passing a resultCode and the bundle of extras
        rec.send(Activity.RESULT_OK, bundle);
        /*Calling rec.send will trigger the onReceiveResult callback to be called
        * within our Activity and the return value will be displayed in the toast
        * in this case*/

    }
}
//Now we can use this service within our application
//==> 2. Registering the IntentService in AndroidManifest.xml
