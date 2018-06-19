package com.example.vinhcrazyyyy.androidbaseproject.google_maps.marker_clustering;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;

public class MarkerClusterUtils {

    private void setClickEventOnClusterItem(ClusterManager clusterManager) {
        clusterManager.setOnClusterItemClickListener(new OnClusterItemClickListener() {
            @Override
            public boolean onClusterItemClick(ClusterItem clusterItem) {
                //Do a click thing here
                return false;
            }
        });
    }
}
