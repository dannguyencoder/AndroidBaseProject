package com.example.vinhcrazyyyy.androidbaseproject.google_maps.OverlaysUtils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnGroundOverlayClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class GroundOverlaysUtils {
    public void createGroundOverlay(GoogleMap map) {
        LatLng NEWARK = new LatLng(40.714086, -74.228697);

        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.newark_hj_1922))
                .position(NEWARK, 8600f, 6500f);

        //Add an overlay to the map, retaining a handle to the GroundOverlay object
        GroundOverlay imageOverlay = map.addGroundOverlay(newarkMap);
    }

    public void removeAnOverlay(GroundOverlay imageOverlay) {
        imageOverlay.remove();
    }

    public void changeAnOverlay(GoogleMap map, GroundOverlayOptions newGroundOverlayOptions) {
        //Add an overlay, retaining a handle to the GroundOverlay object
        GroundOverlay imageOverlay = map.addGroundOverlay(newGroundOverlayOptions);

        //Update the GroundOverlay with a new image of the same dimensions
        imageOverlay = map.setImage(BitmapDescriptorFactory.fromResource(R.drable.newark_hj_1975));
    }

    public void positionGroundOverlay() {
        //There are two ways to specify the position of ground overlay
        // 1. Use a LatLng to center the overlay, and dimensions in meters to specify the size of the image
        // 2. Using a LatLngBounds to specify the north east and south west corners of the image

        //Use location to position an image
        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.newark_hj_1922))
                .anchor(0, 1)
                .position(new LatLng(40.714086, -74.228697), 8600f, 6500f);

        //Use LatLngBounds to position an image
        LatLngBounds newarkBounds = new LatLngBounds(
                new LatLng(40.712216, -74.22655), // South west corner
                new LatLng(40.773941, -74.12544)  // North east corner
        );
        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawble.newark_nj_1922))
                .positionFromBounds(newarkBounds);
    }

    //store a string description with an ground overlay
    public void associateDataWithGroundOverlay() {
        GoogleMap mMap;
        GroundOverlay mSydneyGroundOverlay;

        mSydneyGroundOverlay = mMap.addGroundOverlay(new GroundOverlayOptions()
        .image(BitmapDescriptorFactory.fromResource(R.drawable.harbour_bridge))
        .position(new LatLng(-33.873, 151.206))
        .clickable(true));

        mSydneyGroundOverlay.setTag("Sydney");
    }

    public void handleGroundOverlayEvents(GoogleMap map) {
        //build the ground overlay
        GroundOverlay groundOverlay = null;
        groundOverlay.setClickable(true);

        map.setOnGroundOverlayClickListener(new OnGroundOverlayClickListener() {
            @Override
            public void onGroundOverlayClick(GroundOverlay groundOverlay) {
                //do something with ground overlay
            }
        });
    }
}
