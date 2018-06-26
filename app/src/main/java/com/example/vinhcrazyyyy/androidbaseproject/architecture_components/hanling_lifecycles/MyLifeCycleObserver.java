package com.example.vinhcrazyyyy.androidbaseproject.architecture_components.hanling_lifecycles;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

public class MyLifeCycleObserver implements LifecycleObserver {

    public static final String TAG = MyLifeCycleObserver.class.getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        //This method is called AFTER LifecycleOwner(Activity/Fragment) onCreate() is called
        Log.e(TAG, "onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        //This method is called AFTER LifecycleOwner(Activity/Fragment) onStart() is called
        Log.e(TAG, "onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        //This method is call AFTER LifeCycleOwner(Activity/Fragment) onResume() is called
        Log.e(TAG, "onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        //The method is called BEFORE LifeCycleOwner(Activity/Fragment) onPause() is called
        Log.e(TAG, "onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        //This method is called BEFORE LifeCycleOwner(Activity/Fragment) onStop() is called
        Log.e(TAG, "onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        //This method is called BEFORE LifeCycleOwner(Activity/Fragment) onDestroy() is called
        Log.e(TAG, "onDestroy: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void any() {
        //This method is called on any Lifecycle changes happens
        Log.e(TAG, "onAny: ");
    }
}
