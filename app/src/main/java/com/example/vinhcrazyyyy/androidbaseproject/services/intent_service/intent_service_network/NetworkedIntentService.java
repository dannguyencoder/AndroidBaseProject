package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/*Networking with IntentService
* If you intent to perform networking within the IntentService, keep in mind
* that you do not necessarily need to be concerned about blocking the primary thread.
* The service is already running in the background so you will want to
* "avoid executing AsyncTask within a Service". Instead, for simple operations,
* you can send networking request synchronously. For example, when using an IntentService
* with the Android Async HTTP library
* https://github.com/loopj/android-async-http
* you need to use the synchronous client SyncHttpClient instead of the
* default asynchronous version
* */
public class NetworkedIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NetworkedIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Send synchronous request
        //because IntentService already create a new thread for this
        aClient.doNetWorkStuff(
                //onSuccess here
        );
        //If you try to send asynchronous requests
        // (maybe it is provided by the network library)
        //you will get errors about the thread no longer exists
        //since the service will terminate before the network requests complete
    }
}
