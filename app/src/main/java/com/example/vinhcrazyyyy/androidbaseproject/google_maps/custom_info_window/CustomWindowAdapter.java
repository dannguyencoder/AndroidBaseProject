package com.example.vinhcrazyyyy.androidbaseproject.google_maps.custom_info_window;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhcrazyyyy.androidbaseproject.R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

class CustomWindowAdapter implements InfoWindowAdapter {

    LayoutInflater mInlater;

    public CustomWindowAdapter(LayoutInflater layoutInflater) {
        mInlater = layoutInflater;
    }

    //This changes the frame of the info window; returning null uses the default frame
    //This is just the border and arrow surrounding the contents specified below
    //this give us complete control over both window frame and the content
    //but only one of this can be overridden (one of getInfoWindow OR getInfoContent)
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    //This defines the contents within the info window based on the marker
    //this use the default window frame, and just customize the content
    @Override
    public View getInfoContents(Marker marker) {
        //Getting view from the layout file
        View v = mInlater.inflate(R.layout.custom_info_window, null);

        //Load image to the window
//        ImageView imageView = v.findViewById(R.id.an_image_view_on_the_window_layout);
//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

        //Populate fields
        TextView title = v.findViewById(R.id.tv_info_window_title);
        title.setText(marker.getId());

        TextView description = v.findViewById(R.id.tv_info_window_description);
        description.setText(marker.getSnippet());
        //Return info window contents
        return v;
    }
}
