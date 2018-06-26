package com.example.vinhcrazyyyy.androidbaseproject.services.custom_services;

/*Background Services with IntentService
* One very common use case for services is to generate a background service
* to process a defined task. Once the task is completed, the background service shuts down.
* If you want a simple service that fires up, does a job, and then completes,
* you'll want to leverage the IntentService as your first tool for the job.
* The IntentService start up a new service running a HandlerThread
* which processes incoming work until the queue is empty and then shuts down automatically.
*
* However, IntentService does have a few limitations. The biggest limitation is that
* the IntentService uses a "single worker thread" to handle start requests "one at a time"
* in sequence. As long as you don't require that your service handles multiple requests
* simultaneously, the IntentService should work just fine. One other limitation is that
* "IntentService" shuts down automatically when the worker queue is empty rather than
* waiting to be told to stop.
*
* In specialized cases where you do need background tasks to be processed in parallel
* using a concurrent thread pool, IntentService should not be used and we will
* extend from Service directly. The rest of this guide is focused on the case where
* we cannot use an IntentService*/

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

/*Defining a custom Service
* First you define a class within your application that extends Service and defines
* the onStartCommand which describes the work to do when this intent is executed
* */
public class MyCustomService extends Service {

    private volatile HandlerThread mHandlerThread;
    private ServiceHandler mServiceHandler;

    /*Communicating with the Service
    * If an Activity or other component wants to communicate with a service,
    * the "LocalBroadcastManager" can be used. The service can
    * send messages through a local broadcast that will be received by the Activity/Fragment.
    * A broadcast can be sent anytime to the Activity/Fragment from a service with:
    * */
    private LocalBroadcastManager mLocalBroadcastManager;
    public static final String ACTION = "com.my.app.MyCustomService";

    /*Threading within the Service
    * If you create a custom Service, then you will
    * "need to manage the background threading" yourself using
    * "thread management options" in the thread_management package.
    * In particular, there are two options readily available:
    *
    * 1. Sequential: If you want the service to run a "single worker thread"
    * sequentially processing tasks ==> "use a HandlerThread"
    * 2. Concurrent : If you want the service to run tasks concurrently
    * within a thread pool ==> "use a ThreadPoolExecutor"
    * */

    /*For example, we are going to use a HandlerThread below to process the tasks
    * in the background of the service*/

    //Define how the handler will process the messages
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        //Define how to handle any incoming messages here
        @Override
        public void handleMessage(Message msg) {
            //...
            //When needed, stop the service with
            //stopSelf();
        }
    }

    //Fires when a service is first initialized
    @Override
    public void onCreate() {
        super.onCreate();
        //An Android handler, thread internally operates on a looper
        mHandlerThread = new HandlerThread("MyCustomService.HandlerThread");
        mHandlerThread.start();
        //An Android service handler is a handler running on a specific background thread
        mServiceHandler = new ServiceHandler(mHandlerThread.getLooper());

        //...setting handler thread from before
        //Get access to local broadcast manager
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Fires when a service is started up, do work here!
        //...

        //Send empty message to background thread
        mServiceHandler.sendEmptyMessageDelayed(0, 500);
        //or run code in background
        mServiceHandler.post(new Runnable() {
            @Override
            public void run() {
                //Do something here in the background
                //...

                //Send broadcast out with action filter and extras
                Intent intent = new Intent(ACTION);
                intent.putExtra("result", "baz");
                mLocalBroadcastManager.sendBroadcast(intent);
                /*The service is now sending this local broadcast message
                * to any component that wants to listen for these messages
                * based on the ACTION namespace. Next we construct a new BroadcastReceiver,
                * register to listen and define the onReceive() method to handle
                * the messages within our Activity/Fragment*/

                //If desired, stop the service
                stopSelf();
            }
        })

        //Return "sticky" for services that are explicitly
        //started and stopped as needed by the app
        //Keep the service around "sticky"
        return START_STICKY;
    }
    /*Note that onStartCommand is the method that is triggered when
    an intent triggers the service. The method onStartCommand requires a int
    representing the "mode of operation". There are two additional major modes
    of operation: START_STICKY is used for services that are "explicitly
    started and stopped as needed", while START_NOT_STICKY or START_REDELIVER_INTENT
    are used for services that should only remain running while processing
    any commands sent to them */

    /*Binding is another way to communicate between service and activity
    * Not needed here, local broadcasts will used instead*/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        //Cleanup service before destruction
    }
}
/*At the core, this is all that is required to define the skeleton of a service. Howeverm
* remember that the custom service "runs in your app's main thread and process by default."
* We need to manage the background thread(s) that will execute tasks within our service.
* But first, let's register our service in the manifest.*/
