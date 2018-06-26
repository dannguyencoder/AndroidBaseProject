package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import cz.msebera.android.httpclient.Header;

public class IntentServiceNetworkExample extends IntentService {

    public static final String LOG_TAG = "IntentServiceNetworkExample";
    public static final String INTENT_URL = "INTENT_URL";
    public static final String INTENT_STATUS_CODE = "INTENT_STATUS_CODE";
    public static final String INTENT_HEADERS = "INTENT_HEADERS";
    public static final String INTENT_DATA = "INTENT_DATA";
    public static final String INTENT_THROWABLE = "INTENT_THROWABLE";

    private final AsyncHttpClient aClient = new SyncHttpClient();

    public IntentServiceNetworkExample() {
        super("ExampleNetworkIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceNetworkExample(String name) {
        super(name);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(LOG_TAG, String.valueOf(startId));
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null && intent.hasExtra(INTENT_URL)) {
            aClient.get(this, intent.getStringExtra(INTENT_URL),
                    new AsyncHttpResponseHandler() {

                        @Override
                        public void onStart() {
                            super.onStart();
                            sendBroadcast(new Intent(IntentServiceFragment.ACTION_START));
                            Log.d(LOG_TAG, "onStart: ");
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Intent broadcast = new Intent(IntentServiceFragment.ACTION_SUCCESS);
                            broadcast.putExtra(INTENT_STATUS_CODE, statusCode);
                            broadcast.putExtra(INTENT_HEADERS, HeadersUtils.serializeHeaders(headers));
                            broadcast.putExtra(INTENT_DATA, responseBody);
                            sendBroadcast(broadcast);
                            Log.d(LOG_TAG, "onSuccess: ");
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Intent broadcast = new Intent(IntentServiceFragment.ACTION_FAILURE);
                            broadcast.putExtra(INTENT_STATUS_CODE, statusCode);
                            broadcast.putExtra(INTENT_HEADERS, HeadersUtils.serializeHeaders(headers));
                            broadcast.putExtra(INTENT_DATA, responseBody);
                            broadcast.putExtra(INTENT_THROWABLE, error);
                            sendBroadcast(broadcast);
                            Log.d(LOG_TAG, "onFailure: ");
                        }

                        @Override
                        public void onCancel() {
                            sendBroadcast(new Intent(IntentServiceFragment.ACTION_CANCEL));
                            Log.d(LOG_TAG, "onCancel: ");
                        }

                        @Override
                        public void onRetry(int retryNo) {
                            sendBroadcast(new Intent(IntentServiceFragment.ACTION_RETRY);
                            Log.d(LOG_TAG, String.format("onRetry: &d", retryNo));
                        }

                        @Override
                        public void onFinish() {
                            sendBroadcast(new Intent(IntentServiceFragment.ACTION_FINISH);
                            Log.d(LOG_TAG, "onFinish: ");
                        }
                    });
        }
    }
}
