package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_result_receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/*4. Communicating with a ResultReceiver
* In many cases, an IntentService only needs to communicate with the activity
* or application that spawns it. If this is the case, where only the parent
* application needs to receive data, then let's take a look at a simple way
* to communicate using a ResultReceiver. First, let's define our ResultReceiver
* which should manage communication via method callbacks: */

/*Define a generic receiver used to pass data to Activity from a Service*/
public class MyResultReceiver extends ResultReceiver {

    private Receiver receiver;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    //Constructor takes a handler
    public MyResultReceiver(Handler handler) {
        super(handler);
    }

    //Setter for assigning the receiver
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    //Defines our event interface for communication
    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    //Delegate method which passes the result to the receiver if the receiver has been assigned

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (receiver != null) {
            receiver.onReceiveResult(resultCode, resultData);
        }
    }
}
/*This class is a simple intermediary that can then be used to trigger callbacks
* from the service in order to pass events to the parent Activity/Fragment.
* This is useful when you want to act on the result of the service.
* Next, when we want to trigger the service to start, we just need to pass the IntentService
* a reference to the receiver and then setup a receiver callback*/
