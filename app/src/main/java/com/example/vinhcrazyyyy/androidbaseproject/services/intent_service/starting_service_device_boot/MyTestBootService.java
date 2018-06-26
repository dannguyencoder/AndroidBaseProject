package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.starting_service_device_boot;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.WakefulBroadcastReceiver;

//4. Create the Boot Intent Service
class MyTestBootService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyTestBootService(String name) {
        super(name);
    }

    /*Don't forget to "release the wake lock" within onHandleIntent()
            so that the device can go back to sleep after the service is launched*/
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Release the wake lock provided by the WakefulBroadcastReceiver
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
    /*With this completed, our service will start automatically whenever
    * the device boots*/

    /*Custom Services
    * In 90% of cases when you need a background service, you will grab IntentService
    * as your tool. However, IntentService does have few limitations. The biggest limitation
    * is that the IntentService uses a "single worker thread" to handle
    * start request "one at a time" and process them serially. Therefore, as long as
    * you don't require that your service handle multiple requests simultaneously,
    * typically the IntentService is best tool for the job
    *
    * However, in certain specialized cases where you do need the requests to be processed
    * in parallel, you cannot use the IntentService and instead might want to
    * extend from Service directly. Note that the base Service by default
    * "run in the same process" as the application in which it is declared and
    * in the "main UI thread" of that application. To avoid impacting application performance,
    * you have to "manage your own threading" within the Service
    *
    * */
}
