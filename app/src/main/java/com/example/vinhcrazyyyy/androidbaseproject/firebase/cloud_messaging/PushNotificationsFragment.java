package com.example.vinhcrazyyyy.androidbaseproject.firebase.cloud_messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class PushNotificationsFragment extends BaseFragment {

    private static final String TAG = PushNotificationsFragment.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        txtRegId = getView().findViewById(R.id.txt_reg_id);
        txtMessage = getView().findViewById(R.id.txt_push_message);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //checking for type intent filer
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    //gcm successfully registered
                    //now subscribe to global topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    //new push notification is received

                    String message = intent.getStringExtra("message");
                    Toast.makeText(context, "Push Notification: " + message, Toast.LENGTH_SHORT).show();
                    txtMessage.setText(message);
                }
            }
        };

        displayFirebaseRegId();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //Fetches reg id from shared preferences
    //and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getActivity().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId)) {
            txtRegId.setText("Firebase Reg Id: " + regId);
        } else {
            txtRegId.setText("Firebase Reg Id is not received yet");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //register GCM registration complete receiver
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        //register new push message receiver
        //by doing this, the activity will be notified each time a new message arrive
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        //clear the notification area when app is opened
        NotificationUtils.clearNotifications(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_push_notifications;
    }
}
