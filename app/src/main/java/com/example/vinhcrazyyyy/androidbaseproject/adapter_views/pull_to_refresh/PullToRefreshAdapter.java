package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.pull_to_refresh;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PullToRefreshAdapter extends RecyclerView.Adapter<PullToRefreshAdapter.ViewHolder>{

    List<Tweet> items;

    public PullToRefreshAdapter(List<Tweet> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //Clean all elements of the recycler
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    //Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
