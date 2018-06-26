package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_result_receiver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_result_receiver.MyResultReceiver.Receiver;

import static android.app.Activity.RESULT_OK;

/*3. Executing the IntentService
* Once we have defined the service, let's take a look at how to trigger the service
* and pass the service data. This is done using the same Intent system we are already
* familiar with. We simply create an intent like normal specifying the IntentService
* to execute*/
public class IntentServiceResultReceiverFragment extends BaseFragment {

    public MyResultReceiver receiverForTest;

    /*Call launchIntentService() in the activity/fragment
    * to start the service*/
    public void launchTestService() {
        //Construct our Intent specifying the Service
        Intent i = new Intent(getContext(), MyIntentServiceResultReceiver.class);
        //Add extras to the bundle
        i.putExtra("foo", "bar");
        //Passing receiver to the bundle,
        // in order to setup receiver in the IntentService (to send result back
        // to the activity)
        i.putExtra("receiver", receiverForTest);
        //Start the service
        getContext().startService(i);
        /*You can start the IntentService from any Activity or Fragment at anytime
        * during your application. Once you call startService(), the IntentService
        * does the work defined in its onHandleIntent() method, and then stops itself*/
    }
    /*==> Communicating from a Service to an Application
    * The next step is to be able to communicate data from the IntentService back
    * to the Application. This allows the application to act based on the results
    * of the IntentService. This is done using one of the two approaches:
    *
    * 1. ResultReceiver - Generic callback interface for sending results between
    * service and activity. If your service "only needs to connect with
    * its parent application* in a single place, use this approach.
    *
    * 2. BroadcastReceiver - Used to create a generic broadcast event which can then
    * be picked up by any application. If your service "needs to communicate with
    * multiple coponents" that want to listen for communication, use this approach
    * */

    /*==> 4. Communicating with a ResultReceiver*/

    @Override
    protected void setup(View view) {

    }

    /*Next, when we want to trigger the service to start,
     *we just need to pass the IntentService
     *a reference to the receiver and then setup a receiver callback**/
    //Setup the callback for when data is received from the service
    public void setupServiceReceiver() {
        //This is where we specify what happens when data is received from the service
        receiverForTest = new MyResultReceiver(new Handler());
        //This is where we specify what happens when data is received from the service
        receiverForTest.setReceiver(new Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                    Toast.makeText(getContext(), resultValue, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*Now that we have created the Receiver and defined a Receiver callback in the Activity,
    * we can now freely send message to our Activity at anytime within the Service
    * by accessing the Receiver*/

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
