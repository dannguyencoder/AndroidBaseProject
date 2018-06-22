package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.infinite_pagination;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinhcrazyyyy.androidbaseproject.adapter_views.infinite_pagination.TweetAdapter.ViewHolder;

public class TweetAdapter extends PagedListAdapter<Tweet, ViewHolder> {

    protected TweetAdapter(@NonNull ItemCallback<Tweet> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //getItem() should be used with ListAdapter
        Tweet tweet = getItem(position);

        //null placeholders if the PagedList is configured to use them
        //only works for data sets that have total count provided (i.e. PositionalDataSource)
        if (tweet == null) {
            return;
        }
        //handle remaining work here
        //...
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
