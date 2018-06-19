package com.example.vinhcrazyyyy.androidbaseproject.google_maps.marker_clustering;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class MarkerClusterFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpCluster();
    }

    private void setUpCluster() {
        //Declare a variable for the cluster manager
        ClusterManager<MyItem> myClusterManager;

        List<MyItem> myItems;

        //Initialize the manager
        myClusterManager = new ClusterManager<MyItem>(getContext(), getMap());

        //Set our custom renderer
        myClusterManager.setRenderer(new MyItemRenderer());

        //Point the map's listeners at the listeners implemented by the cluster manager
        //This will later allow onClusterItemClick to work
        getMap().setOnMarkerClickListener(myClusterManager);

        //Add cluster items (markers) to the cluster manager
        myItems = yourWayOfPopulating();
        myClusterManager.addItems(myItems);
        //Let the cluster manager know you've made changes
        myClusterManager.cluster();
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
