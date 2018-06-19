package com.example.vinhcrazyyyy.androidbaseproject.google_maps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;
import com.example.vinhcrazyyyy.androidbaseproject.google_maps.custom_info_window.CustomWindowAdapter;
import com.example.vinhcrazyyyy.androidbaseproject.google_maps.custom_info_window.InfoWindowRefresher;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class MyMapsFragment extends BaseFragment {

    MapView mapFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mapFragment == null) {
            mapFragment = getView().findViewById(R.id.map);

            //Check if was successfully in obtaining the map
            if (mapFragment != null) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        loadMap(googleMap);
                    }
                });
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //The Map is verified. It is now safe to manipulate the map
    private void loadMap(GoogleMap googleMap) {
        if (googleMap != null) {

        }
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.my_google_maps_fragment;
    }

}
