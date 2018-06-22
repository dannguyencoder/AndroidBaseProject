package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.infinite_pagination;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;
import com.example.vinhcrazyyyy.androidbaseproject.adapter_views.infinite_pagination.Datasource.TweetDataSourceFactory;

public class PagingListFragment extends BaseFragment {

    TweetAdapter tweetAdapter;

    //normally this data should be encapsulated in ViewModels, but shown here for simplicity
    LiveData<PagedList<Tweet>> tweets;

    // Should be in the ViewModel but shown here for simplicity
    TweetDataSourceFactory factory;

    @Override
    protected void setup(View view) {
        //setup rest of TweetAdapter here (i.e. LayoutManager)

        //initial page size to fetch can also be configured here too
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(20).build();

        //Passing in dependency
        factory = new TweetDataSourceFactory(RestClientApp.getRestClient());

        tweets = new LivePagedListBuilder(factory, config).build();

        tweets.observe(this, new Observer<PagedList<Tweet>>() {
            @Override
            public void onChanged(@Nullable PagedList<Tweet> tweets) {
                tweetAdapter.submitList(tweets);
            }
        });

        /*Finally, we can use the reference to the data source to call invalidate(),
        which should trigger the data source to reload the data
        and call loadInitial() again:*/
        // Pass in dependency
        final SwipeRefreshLayout swipeContainer = view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                factory.postLiveData.getValue().invalidate();
            }
        });

        /*Once a swipe to refresh is triggered and a new set of data is retrieved,
        we simply need to call setRefreshing(false) on the SwipeRefreshLayout:*/
        tweets.observe(this, new Observer<PagedList<Tweet>>() {
            @Override
            public void onChanged(@Nullable PagedList<Tweet> tweets) {
                tweetAdapter.submitList(tweets);
                swipeContainer.setRefreshing(false);
            }
        });
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_paging_list;
    }
}
