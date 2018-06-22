package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.infinite_pagination.Datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.vinhcrazyyyy.androidbaseproject.adapter_views.infinite_pagination.Tweet;

import okhttp3.OkHttpClient;

/*A data source factory simply creates the data source.
Because of the dependency on the Twitter client,
we need to pass it here too:*/
public class TweetDataSourceFactory extends DataSource.Factory<Long, Tweet> {

    OkHttpClient okHttpClient;

    public TweetDataSourceFactory(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public DataSource<Long, Tweet> create() {
        TweetItemKeyedDataSource tweetItemKeyedDataSource = new TweetItemKeyedDataSource(this.okHttpClient);

        //Use with SwipeRefreshLayout
        //keep reference to the data source with a MutableLiveData reference
        postLiveData = new MutableLiveData<>();
        postLiveData.postValue(tweetItemKeyedDataSource);

        return tweetItemKeyedDataSource;


    }

    /*Using with SwipeRefreshLayout

    In order to use the paging library with SwipeRefreshLayout,
    we need to be able to invalidate the data source to force a refresh.
    In order to do so, we first need a reference to the data source.
    We can do so by creating a MutableLiveData instance, which is lifecycle aware,
    that will hold this value. */

    //use to hold a reference to the
    public MutableLiveData<TweetItemKeyedDataSource> postLiveData;


}
