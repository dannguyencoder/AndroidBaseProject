package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.endless_scrolling.recycler_view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;

public class EndlessScrollRecyclerViewFragment extends BaseFragment {

    //Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void setup(View view) {
        //Configure the RecyclerView
        RecyclerView rvItems = view.findViewById(R.id.rvContacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvItems.setLayoutManager(linearLayoutManager);
        //Retain an instance so that you can call resetState() for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener() {
            @Override
            protected void onLoadMore(int currentPage, int totalItemCount, RecyclerView recyclerView) {
                //Triggered only when new data needs to be appended to the list
                //And whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(currentPage);
            }
        };
        //Adds the scroll listener to RecyclerView
        rvItems.addOnScrollListener(scrollListener);
    }

    //Append the next page of data to the adapter
    //The method probably sends out a network request and appends new data items
    //to your adapter
    private void loadNextDataFromApi(int currentPage) {
        //Send an API request to retrieve appropriate paginated data
        // --> Send the request including an offset value (i.e 'page') as a query parameter
        // --> Deserialize and construct new model objects from the API response
        // --> Append the new data objects to the existing set of items inside the array of items
        // --> Notify the adapter of the new items made with notifyItemRangeInserted()
    }

    private void resetEndlessScrollState() {
        //1. First, clear the array of data
        listOfItems.clear();
        //2. Notify the adapter of the update
        recyclerViewAdapterOfItems.notifyDataSetChanged(); //or notifyItemRangeRemoved()
        //3. Reset endless scroll listener when performing a new search
        scrollLisner.resetState();
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
