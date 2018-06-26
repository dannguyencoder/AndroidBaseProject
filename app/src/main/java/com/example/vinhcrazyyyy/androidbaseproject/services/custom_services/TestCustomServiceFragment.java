package com.example.vinhcrazyyyy.androidbaseproject.services.custom_services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Toast;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

public class TestCustomServiceFragment extends BaseFragment {

    /*Once we have defined the service, let's take a look at how to trigger the service
    * and pass the service data. This is done using the same Intent system we are already
    * familiar with. We simply create an intent like normal specifying the Service
    * to execute*/

    //Call launchTestService to initialize the service
    public void launchTestService() {
        //Construct our Intent specifying the service
        Intent i = new Intent(getContext(), MyCustomService.class);
        //Add extras to the bundle
        i.putExtra("foo", "bar");
        //Start the service
        getContext().startService(i);

        /*you can start the Service from any Activity for Fragment at anytime during your
        * application. Once you call startService(),
        * the Service fires the method onStartCommand() method that runs
        * until the service is explicitly shutdown.*/
    }

    @Override
    protected void setup(View view) {

    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Register for the particular broadcast based on ACTION string
        IntentFilter filter = new IntentFilter(MyCustomService.ACTION);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(testReceiver, filter);
        //or registerReceiver(testReceiver, filter) for a normal broadcast
    }

    @Override
    public void onPause() {
        super.onPause();
        //Unregister the listener when the application is paused
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(testReceiver);
        //or unregisterReceiver(testReceiver) for a normal broadcast
    }

    //Define the callback for what to do when message is received
    private BroadcastReceiver testReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        }
    };
    /*Keep in mind that any activity or other component in this app can listen
    * for the messages using the same approach. This is what makes the BroadcastReceiver
    * a powerful approach for communication between services and activities.*/

    /*Stopping the service
    * Now that when a service is started, it has a lifecycle that's
    * "independent of the component that started it" and the service can run
    * in the background indefinitely, even if the component that started it is destroyed.
    * As such the service should stop itself when its job is done by calling stopSelf(),
    * or the activity (or other component) can stop it by calling stopService()*/

    /*Bound Services
    * In this section above, we outlined how to communicate between an activity/fragment
    * and a service using the LocalBroadcastManager to send and receive messages
    * powered by the Intent messaging system.
    *
    * Note that there is an additional concept of a "bound service" which allows
    * components (such as activities) to bind to the service, send requests,
    * receive responses, and even perform interprocess communication (IPC).
    * The bound service uses "AIDL"
    * http://developer.android.com/guide/components/aidl.html
    * to communicate via an RPC protocol.
    * Since passing data using AIDL is quite tedious and verbose, the more efficient
    * approach if bound communication is desired is to use the convenient
    * Messenger system which wraps the binder into a much easier to use Handler object.
    *
    * Note that in 99% of cases, the LocalBroadcastManager explained in a previous section
    * should be favoured to the bound Messenger approach to communication. In most cases,
    * LocalBroadcastManager is just as fast, secure and significantly more robust.
    * Messenger's and AIDL's are mainly used when your app needs to "communicate to
    * other process" via IPC.
    * */

    /*Learn more about Services
    * There is quite a bit more that can be done to configure services.
    * See the "official services guide" for more of that detail
    * http://developer.android.com/guide/components/services.html
    * */
}
