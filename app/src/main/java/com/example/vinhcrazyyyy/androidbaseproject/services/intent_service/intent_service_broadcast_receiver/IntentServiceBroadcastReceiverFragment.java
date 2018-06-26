package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Toast;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class IntentServiceBroadcastReceiverFragment extends BaseFragment {
    //...onCreate...

    //Launching the service
    public void onStartService(View v) {
        Intent i = new Intent(getContext(), MyIntentServiceBroadcastReceiver.class);
        i.putExtra("foo", "bar");
        getContext().startService(i);
    }

    //Define the callback for what to do when data is received
    private BroadcastReceiver testReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                String resultValue = intent.getStringExtra("resultValue");
                Toast.makeText(getContext(), resultValue, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        //Register for the particular broadcast based on ACTION string
        IntentFilter filter = new IntentFilter(MyIntentServiceBroadcastReceiver.ACTION);
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

    /*Keep in mind that any application can "listen" for the messages
    * using the same approach. This is what makes the BroadcastReceiver a more powerful
    * approach for communication between services and activities/fragments.
    * */

    @Override
    protected void setup(View view) {

    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
