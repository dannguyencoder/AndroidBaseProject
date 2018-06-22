package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.pull_to_refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

//The same method applies for ListView
public class PullToRefreshFragment extends BaseFragment{

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void setup(View view) {
        //Look up thin swipe container view
        swipeContainer = view.findViewById(R.id.swipeContainer);

        //Set up refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Your code to refresh the list here
                //Make sure you call swipeContainer.setRefreshing(false);
                //one the network request has completed successfully
                fetchMoreDataAsyc(0);

                //To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Stop animation (This will be after 3 seconds)
                        swipeContainer.setRefreshing(false);
                    }
                }, 4000);
            }
        });

        //Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void fetchMoreDataAsyc(int page) {
        //Send the network request to fetch the updated data
        //client here is an instance of Android Async HTTP
        //makeNetworkCall is an example endpoint

        mNetworkClient mNetworkClient;

        mNetworkClient.makeNetworkCall(
                new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("Debug", "Fetch timeline error: " + e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //Remember to CLEAR OUT old items before appending to new ones
                        adapter.clear();

                        //...the data has come back, add new items to your adapter
                        adapter.addAll(...);

                        //Now we call setRefreshing(false) to signal refresh has finished
                        swipeContainer.setRefreshing(false);
                    }
                }
        );
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_pull_to_refresh;
    }
}
