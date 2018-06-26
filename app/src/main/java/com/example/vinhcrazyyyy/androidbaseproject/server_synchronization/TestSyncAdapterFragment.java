package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.server_synchronization.ArticleContract.Articles;

/*How to use your new SyncAdapter
* You now  have created the wonderful ability of server synchronization!
* Here's an example of how to use SyncAdapter*/

/**
 * Your SyncAdapter is good to go
 * Your SyncAdapter will run all on its own by Android if you specified it to sync
 * automatically and periodically. If not, you can force a sync using our performSync()
 * method we need
 *
 * Use {@link android.database.ContentObserver} to get callbacks for data changes
 * when Android runs your SyncAdapter or when you manually run it.*/
public class TestSyncAdapterFragment extends BaseFragment {

    /**
     * This is our example content observer
     * */
    private ArticleObserver articleObserver;

    @Override
    protected void setup(View view) {
        //Create your sync account
        AccountGeneral.createSyncAccount(getActivity());

        //Perform a manual sync by calling this
        SyncAdapter.performSync();

        //Setup example content observer
        articleObserver = new ArticleObserver();
    }

    @Override
    public void onStart() {
        super.onStart();

        //Register the observer at the start of our activity
        getContext().getContentResolver().registerContentObserver(
                ArticleContract.Articles.CONTENT_URI, //Uri to observe (our articles)
                true, //Observe its descendants
                articleObserver //The observer
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        if (articleObserver != null) {
            //Unregister the observer at the stop of our activity
            getContext().getContentResolver().unregisterContentObserver(articleObserver);
        }
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }

    /**
     * Example content observer for observing article data changes
     * */
    private final class ArticleObserver extends ContentObserver {

        public ArticleObserver() {
            //Ensure callbacks happen on the UI thread
            super(new Handler(Looper.getMainLooper()));
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            //Handle your data changes here !!!
            refreshArticles();
        }
    }

    private void refreshArticles() {
        Log.i(getClass().getName(), "Articles data has changed!");
    }
}
