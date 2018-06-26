package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Create a SyncService
 * For Android to use our SyncAdapter, it needs to run it in bound Service
 * that will allow it to do so.
 * To see the in-depth of how AbstractThreadedAdapter works read about AIDL
 * for inter-process communication
 * We need to create this bound Service so we can let Android use our SyncAdapter class*/
public class SyncService extends Service {

    /**
     * Lock use to synchronize instantiation of SyncAdapter
     * */
    private static final Object LOCK = new Object();
    private static SyncAdapter syncAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        //SyncAdapter is not Thread-safe
        synchronized (LOCK) {
            //Instantiate our SyncAdapter
            syncAdapter = new SyncAdapter(this, false);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //Return our SyncAdapter's IBinder
        return syncAdapter.getSyncAdapterBinder();
    }
}
/**
 * Declare SyncService
* We need to declare our SyncService in the manifest.xml file
 * */
