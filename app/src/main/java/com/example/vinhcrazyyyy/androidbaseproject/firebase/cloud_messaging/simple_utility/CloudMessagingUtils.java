package com.example.vinhcrazyyyy.androidbaseproject.firebase.cloud_messaging.simple_utility;

import com.google.firebase.messaging.FirebaseMessaging;

public class CloudMessagingUtils {
    public void subscribingToTopicBasedMessages() {
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/" + "dogs");
    }
}
