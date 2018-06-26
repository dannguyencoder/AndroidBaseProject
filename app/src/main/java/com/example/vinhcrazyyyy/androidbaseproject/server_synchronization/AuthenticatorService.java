package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

/**
 * For Android to use our AccountAuthenticator, it needs to run it
 * in a bound Service that will allow it to do so. To see the in-depth
 * of how AbstractAccountAuthenticator works, look at its Transport inner-class
 * and read about AIDL for inter-process communication
 *
 * We need to create this bound service so we can let Android
 * use our AccountAuthenticator class*/

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * This is used only by Android to run our {@link AccountAuthenticator}
 * */
public class AuthenticatorService extends Service{

    private AccountAuthenticator authenticator;

    @Override
    public void onCreate() {
        //Instantiate our authenticator when the service is created
        this.authenticator = new AccountAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //Return the authenticator's IBinder
        return authenticator.getIBinder();
    }

    /**
     * (Optional) Declare Account Preferences
     * These preferences will be shown when accessing the account's preferences
     * from the device Settings screen. This allows the user more control
     * over their account.
     * To create this, we need to create a resource package in 'res' called 'xml',
     * if not already created, and create 'syncsettings.xml'
     *
     * <?xml version = "1.0" encoding = "utf-8"?>
     *     <PreferenceScreen
     *          xmlns:android = "http://schemas.android.com/apk/res/android">
     *          <SwitchPreference
     *              android:key = "use_sync"
     *              android:title = "Toggle Options"
     *              android:summary = "This is an example toggle setting" />
     *          />
     *     </PreferenceScreen>*/

    /**
     * Declare the Authenticator
     * We need to create a resource package in 'res' called 'xml', if not already created,
     * and create 'authenticator.xml'.
     *
     * android:accountType = (string) - identify our account type when an app wants
     * to authenticate us (match EXACTLY in our AccountsGeneral)
     * android:label=(string) - identify our account type when an app wants to authenticate
     * us (match EXACTLY in our AccountsGeneral)
     * android:label=(string) - Name of the account on the device Settings
     * android:icon=(drawable) - is the normal icon that will appear in the 'Accounts'
     * Android settings
     * android:smallIcon=(drawable) - (optional) is the small icon that will show
     * for the account
     * android:accountPreferences=(xml) - (optional) specifies extra settings for our account*/
}
