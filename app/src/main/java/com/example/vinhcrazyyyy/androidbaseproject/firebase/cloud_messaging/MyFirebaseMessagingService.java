package com.example.vinhcrazyyyy.androidbaseproject.firebase.cloud_messaging;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.vinhcrazyyyy.androidbaseproject.AnythingActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage != null) {
            return;
        }

        //Check if message contains a notification payload
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }

        //Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            //Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0) {
                Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

                try {
                    JSONObject json = new JSONObject(remoteMessage.getData().toString());
                    handleDataMessage(json);
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }
            }
        }
    }

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");
            boolean isBackground = data.getBoolean("is_background");
            String imageURL = data.getString("image");
            String timeStamp = data.getString("timeStamp");
            JSONObject payload = data.getJSONObject("payload");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            Log.e(TAG, "isBackground: " + isBackground);
            Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageURL: " + imageURL);
            Log.e(TAG, "timeStamp: " + timeStamp);

            if(!NotificationUtils.isAppInBackground(getApplicationContext())) {
                //app is in background, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                //play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            } else {
                //app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), AnythingActivity.class);
                resultIntent.putExtra("message", message);

                //check for image attachment
                if (TextUtils.isEmpty(imageURL)) {
                    showNotificationMessage(getApplicationContext(), title, message, timeStamp, resultIntent);
                } else {
                    //image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timeStamp, resultIntent, imageURL);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        private void handleNotification (String message){
            if (!NotificationUtils.isAppInBackground(getApplicationContext())) {
                //app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                //play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            } else {
                //If the app is in background, firebase itself handles the notification
            }
        }
    }

    //show notification with text and image
    private void showNotificationMessageWithBigImage(Context applicationContext, String title, String message, String timeStamp, Intent intent, String imageURL) {
        notificationUtils = new NotificationUtils(applicationContext);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageURL);
    }

    //show notification with text only
    private void showNotificationMessage(Context applicationContext, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(applicationContext);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }
    }
