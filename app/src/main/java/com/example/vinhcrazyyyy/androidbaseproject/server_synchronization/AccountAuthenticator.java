package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

/**
 * Create an Account Authenticator
 * The authenticator will perform all the actions on the account type.
 * It will also know which activity to show for the user to enter their credential
 * and where to find any stored auth-token that the server has previously returned.
 * This can also be common to many different services under a single account type.
 *
 * For example, Google's authenticator for Android
 * authenticates the Google Mail service (Gmail).
 * Google Calendar, Google Drive, and along with many other Google services.
 *
 * We need to make an authenticator that extends the AbstractAccountAuthenticator class.
 * This is really simple because we don't use any authentication, therefore, we stub it.*/

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;

/**
 * This is stubbed because we don't need any authentication to access
 * the pretend RSS feed
 * */
public class AccountAuthenticator extends AbstractAccountAuthenticator{
    public AccountAuthenticator(Context context) {
        super(context);
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }
}
