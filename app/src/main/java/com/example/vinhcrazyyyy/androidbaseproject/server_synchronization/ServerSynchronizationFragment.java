package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

/*For most Android applications, maintaining persistent synchronization between
* local and cloud data source can troubling, and frustrating, yet it is essential.
* Luckily, Android provides the SyncAdapter framework to batch synchronization
* efficiently for us!
* The SyncAdapter can be invoked manually or can be set to sync periodically
* at regular intervals. There are also a plethora of other awesome features that using
* the SyncAdapter framework allows, such as account settings and a nice logo
* in the Android settings...which implicates its reliability on the AccountManager
* framework as well.
*
* To use the SyncAdapter framework, you must create a few components:
* 1. ContentProvider (used to update local content)
* 2. Authenticator (can be stubbed or used to retrieve tokens
* such as OAuth tokens)
* 3. AuthenticatorService (Used only by Android to use authenticator if needed)
* 4. SyncAdapter (Performs the actual synchronization)
* 5. SyncService (Used only by Android to run SyncAdapter)
*
* As a hypothetical use case, we'll pretend that we are creating a SyncAdapter
* to synchronize pretend RSS feed with our application herein
* */
public class ServerSynchronizationFragment extends BaseFragment {
    @Override
    protected void setup(View view) {

    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
    /**/
}
