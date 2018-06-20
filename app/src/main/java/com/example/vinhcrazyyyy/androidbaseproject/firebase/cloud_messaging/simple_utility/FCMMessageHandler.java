package com.example.vinhcrazyyyy.androidbaseproject.firebase.cloud_messaging.simple_utility;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

import com.example.vinhcrazyyyy.androidbaseproject.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.RemoteMessage.Notification;

import java.util.Map;

//2. Listening for push notifications
public class FCMMessageHandler extends FirebaseMessagingService {
    private static final int MESSAGE_NOTIFICATION_ID = 435345;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> data = remoteMessage.getData();
        String from = remoteMessage.getFrom();

        Notification notification = remoteMessage.getNotification();
        createNotification(notification);

        //if you subscribe to topic-based message
        String mesage = remoteMessage.getNotification().getBody();
        String from = remoteMessage.getFrom();
        if (from.startsWith("/topics/dogs/")) {
            Log.d("TAG", "get here");
        } else {
            //normal downstream message
        }
    }

    //Creates notification based on title and body received
    private void createNotification(Notification notification) {
        Context context = getBaseContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody());

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
    }

    //The push message is handled by creating a notification
    //There are a few actions that are commonly taken when a push is received
    //1. Create a notification
    //2. Update an Activity
    //3. Launch new Activity
}
