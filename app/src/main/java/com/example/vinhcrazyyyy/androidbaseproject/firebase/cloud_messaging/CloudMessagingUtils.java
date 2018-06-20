package com.example.vinhcrazyyyy.androidbaseproject.firebase.cloud_messaging;

import com.google.firebase.messaging.FirebaseMessaging;

public class CloudMessagingUtils {

    public void subscribeToTopic(String topicName) {
        FirebaseMessaging.getInstance().subscribeToTopic(topicName);
    }

    public void unsubscribeFromTopic(String topicName) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topicName);
    }
}
