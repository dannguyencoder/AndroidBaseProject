package com.example.vinhcrazyyyy.androidbaseproject.firebase.cloud_messaging.simple_utility;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

//1. Create a InstanceID ListenerService
public class MyInstanceIDListenerService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        //Fetch updated Instance ID token and notify of changes
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG", "Refreshed token: " + refreshedToken);

        //If you want to send messages to this application instance or
        //manage this apps subscriptions on the server side, send the
        //Instance ID token to your app server
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        //TODO: Implement this method to send token to your app server
    }
}
