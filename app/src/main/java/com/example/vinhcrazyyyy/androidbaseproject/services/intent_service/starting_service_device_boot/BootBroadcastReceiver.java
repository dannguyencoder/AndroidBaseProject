package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.starting_service_device_boot;

/*Starting a Service at Device Boot
*
* In certain cases, we might want a service to start right after the device boots up.
* This is a specific case of a broader trigger of launching a service when a particular
* broadcast is received by your application. To start a service when a broadcast
* (such as boot message) is received, we can start by adding the necessary permission
* to receive this message in our manifest AndroidManifest.xml in the <manifest> element*/

/*We need to link this boot message with a particular broadcast receiver
    which will receive and processes the "boot" message issued by the phone.
    Second, let's define our broadcast receiver class
    extending from "WakfulBroadcastReceiver" which ensures the device
    will stay awake until service has been started*/

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/*WakefulBroadcastReceiver ensures the device does not go back to sleep
* during the startup of the service*/
//2. Create a WakefulBroadcastReceiver
public class BootBroadcastReceiver extends WakefulBroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        /*Launch the specified service when this message is received*/
        Intent startServiceIntent = new Intent(context, MyTestBootService.class);
        startWakefulService(context, startServiceIntent);
    }

    /*Now that we have created the receiver to start our service, within our manifest
    * AndroidManifest.xml in the <application> element, we need to add our broadcast receiver
    * specifying a fully qualified path:*/
}
