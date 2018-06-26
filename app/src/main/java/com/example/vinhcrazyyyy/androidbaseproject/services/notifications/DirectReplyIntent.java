package com.example.vinhcrazyyyy.androidbaseproject.services.notifications;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.vinhcrazyyyy.androidbaseproject.R;

/*To leverage the direct reply, you need to set up an activity or service
 * to receive it. In the example below, we will set up an Intent Service
 * call DirectReplyIntent. Create this Intent Service and make sure
 * to declare this service in your AndroidManifest.xml file*/
public class DirectReplyIntent extends IntentService {

    public static String KEY_TEXT_REPLY = "key_text_reply";
    public static String KEY_NOTIFY_ID = "key_notify_id";

    public DirectReplyIntent() {
        super("DirectReplyIntent");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DirectReplyIntent(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //handle notification here
        CharSequence directReply = getMessageText(intent);
        if (directReply != null) {
            Notification repliedNotification =
                    new NotificationCompat.Builder(DirectReplyIntent.this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentText("Received: " + directReply)
                    .build();

            NotificationManager notificationManager
                    = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            int notifId = intent.getIntExtra(KEY_NOTIFY_ID, -1);
            notificationManager.notify(notifId, repliedNotification);
        }
    }

    @TargetApi(VERSION_CODES.KITKAT_WATCH)
    private CharSequence getMessageText(Intent intent) {
        //Decode the reply text
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }

    /*The approach can also be used for receiving text in an activity.
    * The only difference is that you need to use PendingIntent.getActivity()
    * instead of PendingIntent.getService(), and receiving data needs
    * to use getIntent() in the activity instead of the onHandleIntent()*/

    //Make sure to include this new Intent Service in your AndroidManifest.xml
}
