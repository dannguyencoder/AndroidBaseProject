package com.example.vinhcrazyyyy.androidbaseproject.google_maps.marker_clustering;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

class MyItemRenderer extends DefaultClusterRenderer<MyItem> {

    public MyItemRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem myItem, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);

        markerOptions.position(myItem.getLatLng())
                .icon(BitmapDescriptorFactory.fromBitmap(getMarker()));
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions);
        //Customize the cluster here
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(getMarker()));
    }
}
