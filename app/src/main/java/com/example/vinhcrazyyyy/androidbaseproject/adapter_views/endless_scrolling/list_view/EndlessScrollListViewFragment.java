package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.endless_scrolling.list_view;

import android.view.View;
import android.widget.ListView;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;

public class EndlessScrollListViewFragment extends BaseFragment {

    /*Make sure setOnScrollListener on the onCreate method of activity
    * or onCreateView of Fragment otherwise you may encountered unexpected issues*/
    @Override
    protected void setup(View view) {
        //... the usual
        ListView lvItems = view.findViewById(R.id.lvItems);

        //Attach the listener to the AdapterView onCreate
        lvItems.setOnScrollListener(new EndlessListViewScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                //Triggered only when new data needs to be appended to the list
                //Add whatever code is needed to append new items to your AdapterView
                loadNextDataFromApi(page);
                //or loadNextDataFromApi(totalItemsCount);
                return true; //ONLY if more data is actually being loaded; false otherwise
            }
        });
    }

    //Append the next page of data into the adapter
    //This method probably sends out a network request and appends new data items
    //to your adapter
    private void loadNextDataFromApi(int page) {
        //Send an API request to retrieve appropriate paginated data
        // --> Send the request including an offset value (i.e "page") as query parameter
        // --> Deserialize and construct new model objects from the API response
        // --> Append the new data objects to the existing set of items inside the array
        //     of items
        // --> Notify the adapter of the new items made with 'notifyDataSetChanged()'

        /*If you see "Cannot call this method in a scroll callback.
        Scroll callbacks might be run during a measure & layout pass where you can"
        You need to do the following onLoadMore() method as outlined in
        http://stackoverflow.com/questions/39445330/cannot-call-notifyiteminserted-method-in-a-scroll-callback-recyclerview-v724-2
        to delay the adapter update*/
        listView.post(new Runnable() {
            @Override
            public void run() {
                //Notify adapter with appropriate notify methods
                adapter.notifyItemRangeInserted(curSize, allContacts.size() - 1);
            }
        });
    }

    /*Now as you scroll, items will be automatically filling in because the onLoadMore method
     will be triggered once the user crosses the visibleThreshold.
     This approach works equally well for a GridView and the listener gives access
     to both the page as well as the totalItemsCount to support both pagination
     and offset based fetching.*/

    /*In order for pagination to continue working reliably, make sure you "clear the adpter"
    * of items before appending new items to the list*/

    /*In order for the pagination to trigger, keep in mind that loadNextDataFromApi() is called
    * New data needs to be "appended to the existing data source".
    * In other words, only clear items from the list on the initial "page"
    * Subsequent "pages" of data should be appended to the existing data*/
    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
