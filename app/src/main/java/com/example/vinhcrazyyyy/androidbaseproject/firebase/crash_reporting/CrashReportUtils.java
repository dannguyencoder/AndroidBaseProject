package com.example.vinhcrazyyyy.androidbaseproject.firebase.crash_reporting;

import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;

import java.lang.Thread.UncaughtExceptionHandler;

public class CrashReportUtils {

    private void sendingASimpleCrashReport() {
        FirebaseCrash.report(new Exception("My first Firebase non-fatal error on Android"));
    }

    //add this block of code in the main Application class to handle all uncaught exception
    //Unlike many other crash reporting libraries that only require one line
    //to initialize crash reporting all through the app, Firebase doesn't provide
    //such utility methods
    //Firebase crash reporting seems to be a separate process
    private void monitorAllCrashExceptionInApp() {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                FirebaseCrash.report(e);
            }
        });
    }

    private void sendCustomLogs() {
        FirebaseCrash.log("MainActivity started");

        //There is also an option to create logcat output
        //Using the Firebase.logcat() function
        FirebaseCrash.logcat(Log.DEBUG, "My tag", "My message");
    }

    private void deObfuscateProguardLabels() {
        //Check your
        //<project-root>/<app-module>/build/outputs/mapping/<build-type>/<appname>-proguard-mapping.txt
        //and upload your  mapping.txt to Firebase
        //mapping.txt is generated for the obfuscated labels.
    }
}
