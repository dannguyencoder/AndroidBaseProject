package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

/*Step 2 & 3: The Account and Authenticator
* The next steps requires that we have an Account on the device.
* This seems like a lot of work or unnecessary, but it is worth it
* for a prodigious amount of reasons:
*
* 1. Supports the SyncAdapter framework
* 2. Supports various access tokens (access rights)
* 3. Supports various account settings features
* 4. Standardization for authentication
* 5. Sharing your account across multiple apps, like Google does
*
* There are many ways you can utilize account creation and authentication
* (check out the reference), but for the scope of our example (syncing RSS feed),
* we just want a very simple account with stubbed authentication*/

/*Declare Proper Permissions
* Before continuing, we must add these NEEDED permissions to the manifest.xml
* so we can use SyncAdapter, AccountManager, and SyncSettings.
*
* <uses-permission android:name = "android.permission.GET_ACCOUNTS"/>
* <uses-permission android:name = "android.permission.MANAGE_ACCOUNTS"/>
* <uses-permission android:name = "android.permission.AUTHENTICATE_ACCOUNTS"/>
* <uses-permission android:name = "android.permission.WRITE_SYNC_SETTINGS"
* <uses-permission android:name = "android.permission.READ_SYNC_SETTINGS"/>
* <uses-permission android:name = "android.permission.READ_SYNC_STATS"/>*/

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

import cz.msebera.android.httpclient.auth.AUTH;

/**
 *
 */
/*Create Contract Class for Account
* Again, it's good idea to concretely define our account structure,
* especially since our account will be really simple.*/
public final class AccountGeneral {
    /**
     * This is the type of account we are using. i.e. we can specify our app or apps
     * to have different types, such as 'read-only', 'sync-only', & 'admin'
     */
    private static final String ACCOUNT_TYPE = "com.example.syncaccount";

    /**
     * This is the name that appears in the Android 'Accounts' settings
     * */
    private static final String ACCOUNT_NAME = "Example Sync";

    /**
     * Gets the standard sync account for our app
     * @return {@link Account}
     * */
    public static Account getAccount() {
        return new Account(ACCOUNT_NAME, ACCOUNT_TYPE);
    }

    /**
     * Creates the standard sync account for our app
     * @param c {@link Context}
     * */
    public static void createSyncAccount(Context c) {
        //Flag to determine  if this is a new account or not
        boolean created = false;

        //Get an account and the account manager
        Account account = getAccount();
        AccountManager manager = (AccountManager) c.getSystemService(Context.ACCOUNT_SERVICE);

        //Attempt to explicitly create the account with no password or extra data
        if (manager.addAccountExplicitly(account, null, null)) {
            final String AUTHORITY = ArticleContract.CONTENT_AUTHORITY;
            final long SYNC_FREQUENCY = 60 * 60 //1 hour (seconds)

            //Inform the system that this account supports sync
            ContentResolver.setIsSyncable(account, AUTHORITY, 1);

            //Inform the system that this account is eligible for auto sync
            //when the network is up
            ContentResolver.setSyncAutomatically(account,AUTHORITY, true);

            //Recommend a schedule for automatic synchronization.
            //The system may modify this based on other scheduled syncs
            //and network utilization
            ContentResolver.addPeriodicSync(account, AUTHORITY, new Bundle(), SYNC_FREQUENCY);

            created = true;
        }

        //Force a sync if the account was just created
        if (created) {
            SyncAdapter.performSync();
        }
    }
}
