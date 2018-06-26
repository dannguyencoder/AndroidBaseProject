package com.example.vinhcrazyyyy.androidbaseproject.services.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.RemoteInput;

import com.example.vinhcrazyyyy.androidbaseproject.R;

import static com.example.vinhcrazyyyy.androidbaseproject.services.notifications.DirectReplyIntent.KEY_NOTIFY_ID;

public class NotificationsUtils {

    Context context;

    public NotificationsUtils(Context context) {
        this.context = context;
    }

    /*Create notifications is fairly straightforward. First you construct
     * using the NotificationCompat.Builder and then you can add the notification
     * to the NotificationManager
     *
     * Beginning with API Level 26, "a notification must belong to a channel"
     * */
    @RequiresApi(api = VERSION_CODES.O)
    private void createNotificationChannel() {
        /*For API Level 26 and above, we need to create a notification channel,
         * provide a unique String id, name, and importance level.
         * Setting the description helps the user to identify the channel.
         * Create the notification channel in the app's Application class,
         * Create a notification channel again with same id updates the name
         * and description with the new values*/

        //Configure the channel
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("myChannelId",
                "MyChannel", importance);
        channel.setDescription("Reminders");
        //Register the channel with notifications manager
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(channel);

        /*An app can create multiple channels with different ids. Also, one can
         * delete existing channels, however, be aware that they are visible
         * in the settings app*/
    }

    private void createNotification() {
        /*Let's build a basic notification using the NotificationCompat.Builder.
         * Typically this will contain at least an icon, a title, body
         *
         * For API level 26 and above, we need to set a notification channel
         * using the updated NotificationCompat.Builder*/
        NotificationCompat.Builder mBuilder =
                //Builder class for devices targeting API 26+ requires a channel ID
                new NotificationCompat.Builder(context, "myChannelId")
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

        /*For API level 25 and below, we create the notification without any channel*/
        NotificationCompat.Builder mBuilder =
                //this Builder class is deprecated
                new NotificationCompat.Builder()
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("My notification")
                        .setContentText("Hello World");

        /*Then we just have to append the notification using the NotificationManager*/
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //mId allows you to update the notification later on
        mNotificationManager.notify(mId, mBuilder.build());
    }

    /*A generic method for creating a simple notification*/
    /*createNotification(56, R.drawable.ic_launcher, "New Message",
        "There is a new message from Bob!")*/
    private void createNotification(int nId, int iconRes, String title, String body) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(iconRes)
                .setContentTitle(title)
                .setContentText(body);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //mId allows you to update the notification later on
        mNotificationManager.notify(nId, mBuilder.build());
    }

    /*Adding an Action*/
    /*Notifications can also have actions attached that the user can perform by clicking.
     * Although having action attached to the notification is optional,
     * you should almost always have at least one action attached.
     *
     * In order for the notification service to launch an intent in the original application
     * without necessarily starting it up, the notification service will need to use the same
     * permissions required bt this application. A pending intent is created,
     * which wraps intent with a token that grants these permissions to
     * the notification service. For more information about pending intents, see
     * the official Android documentations
     * http://developer.android.com/reference/android/app/PendingIntent.html
     * */
    private void addActionToNotification() {
        //First let's define the intent to trigger when notification is selected
        //Start out by creating a normal intent (in this case to open an activity)
        Intent intent = new Intent(context, SomeActivity.class);
        //Next, let's turn this into a PendingIntent using
        //  public static PendingIntent getActivity(Context context, int requestCode,
        //                                  Intent intent, int flags)
        int requestID = (int) System.currentTimeMillis();
        //unique requestID to differentiate various notification with same NotifId
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pItent = PendingIntent.getActivity(context, requestID, intent, flags);
        //Now we can attach the pendingIntent to a new notification using setContentIntent
        Notification noti = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My notification")
                .setContentText("Hello world !")
                .setContentIntent(pItent)
                .setAutoCancel(true) //Hides the notification after it's been selected
                .build();

        //Get the notification manager system service
        NotificationManager mNotificationManager
                 = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //mId allows you to update the notification later on
        mNotificationManager.notify(0, noti);

        /*You can also add up to three additional action buttons as well*/
        //Setup your pending intents first and
        //then call addAction(int icon, CharSequence title, PendingIntent intent)
        Notification noti = new NotificationCompat.Builder(context)
                ...
                .addAction(pItent)
                .addAction(R.drawable.icon, sIntent)
                .addAction(R.drawable.icon, iIntent).build();
    }

    /*Adding Direct Reply Support
    * Android N (API 24) now supports the ability to respond directly
    * to a notification. The UI contains action icons below the notification.
    * In the example below, there are 3 different actions to take:*/
    private void showDirectReplyNotification() {
        //First, let's assume we are setting up the notification without this functionality
        //Use standard notification manager builder class
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Image Download Complete!");

        /*To leverage the direct reply, you need to set up an activity or service
        * to receive it. In the example below, we will set up an Intent Service
        * call DirectReplyIntent. Create this Intent Service and make sure
        * to declare this service in your AndroidManifest.xml file*/

        //Next, we will create a pending intent to launch this service
        //assuming that API version 24 or higher is used:
        if (android.os.Build.VERSION.SDK_INT >= 24) {

            //Setup a DirectReply IntentService
            Intent directReplyIntent = new Intent(context, DirectReplyIntent.class);

            //pass the notification ID -- it should be a UUID
            directReplyIntent.putExtra(DirectReplyIntent.KEY_NOTIFY_ID, 82);

            //only handle one pending intent at a time
            int flags = PendingIntent.FLAG_UPDATE_CURRENT;

            PendingIntent directReplyPendingIntent
                    = PendingIntent.getService(context, 0, directReplyIntent, flags);

            //We need to set the placeholder text and the key name that will be used
            //to store the text by creating a RemoteInput class
            RemoteInput remoteInput = new RemoteInput.Builder(DirectReplyIntent.KEY_TEXT_REPLY)
                    .setLabel("Type Message").build();

            //We need to use this RemoteInput class and create a notification action.
            //This notification action will then be passed to the notification builder class.

            //Generate the notification action
            NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                    R.drawable.ic_launcher, "Reply", directReplyPendingIntent
            ).addRemoteInput(remoteInput).build();
            builder.addAction(action)
        } //end of minimum API version 24 check

        //Finally, we should create the notification. Note that we need to reference
        //notification ID so that it can be used to acknowledge it in the reply.
        //Earlier, we passed this notification ID as part of the pending intent.

        //Create the notification outside the minimum API 24 version check
        Notification noti = builder.build();
        NotificationManager mNotificationManager
                = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //NOTIF_ID allows you to update the notification later on
        mNotificationManager.notify(NOTIF_ID, noti);

        //Finally, we need to setup the DirectReplyIntent service to receive this message
    }

    /*Adding an Expanded View
    * Android 4.1 supports expandable notifications. You can also setup the notification
    * to have an expanded view when pressed which gives additional details.
    * See more about that on the official guide
    *
    * There are three styles to be used with the big view: big picture style,
    * big text style, Inbox style. The following code demonstrate the usage of
    * the BigTextStyle() which allows to use up to 256 dp*/
    private void showExpandedNotification() {
        Notification noti = new NotificationCompat.Builder(context)
                ...
                .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
    }

    private void cancelNotification() {
        //The user can dismiss all notification or if you set your notification
        //to auto-cancel it is also removed once the user selects it. You can
        //also call the cancel() for a specific notification ID
        //on the NotificationManager

        //Store some notification id as nId
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(nId);
    }

    private void addALargeIcon() {
        /*If the notification should display a larger higher-quality icon on the left,
        * we can use the setLargeIcon method to set a large (64 x 64) image as the icon.
        * A common usage of this is to show a picture of the person's face
        * that is associated with the notification*/

        //This can be done with the following code

        //setLargeIcon expects a bitmap
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.my_large_icon);
        //Pass in the bitmap as the large icon
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                ...
                .setLargeIcon(largeIcon);
    }
}
