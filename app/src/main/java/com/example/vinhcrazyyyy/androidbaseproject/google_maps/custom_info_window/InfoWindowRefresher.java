package com.example.vinhcrazyyyy.androidbaseproject.google_maps.custom_info_window;

import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;

class InfoWindowRefresher implements Callback {

    private Marker markerToRefresh;

    public InfoWindowRefresher(Marker marker) {
        this.markerToRefresh = marker;
    }

    @Override
    public void onSuccess() {
        markerToRefresh.showInfoWindow();
    }

    @Override
    public void onError(Exception e) {

    }
}
