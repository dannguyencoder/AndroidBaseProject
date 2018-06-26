package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

public class IntentServiceFragment extends BaseFragment {

    public static final String LOG_TAG = "IntentServiceNetworkExample";
    public static final String ACTION_START = "SYNC_START";
    public static final String ACTION_RETRY= "SYNC_RETRY";
    public static final String ACTION_CANCEL = "SYNC_CANCEL";
    public static final String ACTION_SUCCESS = "SYNC_SUCCESS";
    public static final String ACTION_FAILURE = "SYNC_FAILURE";
    public static final String ACTION_FINISH = "SYNC_FINISH";

    private static final String[] ALLOWED_ACTIONS = {ACTION_START, ACTION_RETRY, ACTION_CANCEL,
    ACTION_SUCCESS, ACTION_FAILURE, ACTION_FINISH};

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            //swith() doesn't support strings in older JDK
            if (ACTION_START.equals(action)) {
                Log.d(LOG_TAG, "Request started");
            } else if (ACTION_CANCEL.equals(action)) {
                Log.d(LOG_TAG, "Request canceled");
            } else if (ACTION_RETRY.equals(action)) {
                Log.d(LOG_TAG, "Request retried");
            } else if (ACTION_FAILURE.equals(action)) {
                Log.d(LOG_TAG, "Request failed");
            } else if (ACTION_SUCCESS.equals(action)) {
                int statusCode = intent.getIntExtra(IntentServiceNetworkExample.INTENT_STATUS_CODE, 0);
                String[] header = intent.getStringArrayExtra(IntentServiceNetworkExample.INTENT_HEADERS);
                byte[] returnedBytes = intent.getByteArrayExtra(IntentServiceNetworkExample.INTENT_DATA);
                if (returnedBytes != null) {
                    String response = new String(returnedBytes);
                }
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter iFilter = new IntentFilter();
        for (String action : ALLOWED_ACTIONS) {
            iFilter.addAction(action);
        }
        getActivity().registerReceiver(broadcastReceiver, iFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void setup(View view) {

    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
