package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.infinite_pagination.Datasource;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.vinhcrazyyyy.androidbaseproject.adapter_views.infinite_pagination.Tweet;

import okhttp3.OkHttpClient;

public class TweetItemKeyedDataSource extends android.arch.paging.ItemKeyedDataSource<Long, Tweet> {

    //pass whatever networking libraries are needed to make the network call
    OkHttpClient okHttpClient;

    public TweetItemKeyedDataSource(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    //Next, we need to define inside the data source the loadInitial() and loadAfter().
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Tweet> callback) {
        //no index should be passed on initial load
        //call api to take data at specified index item position
    }

    //Called repeatedly when more data needs to be set
    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Tweet> callback) {
        //fetch data synchronously

        //params.key & params.requestLoadSize should be used
        //params.key will be the lowest index retrieved
        //and should be used for the index = parameter in the API
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Tweet> callback) {

    }

    //first type of ItemKeyedDataSource should match return type of getKey()
    @NonNull
    @Override
    public Long getKey(@NonNull Tweet item) {
        //item.getPostId() is a Long type
        return item.getPostId();
    }
}
