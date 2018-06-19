package com.example.vinhcrazyyyy.androidbaseproject.google_maps.OverlaysUtils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;

public class TileOverlaysUtils {

    private void createTileOverlays(GoogleMap map) {

        //1. Create the TileProvider
        TileProvider tileProvider = new UrlTileProvider(256, 256) {
            @Override
            public URL getTileUrl(int x, int y, int zoom) {
                //Define the URL Pattern for the tile images
                String s = String.format("http://my.image.server/image/%d/%d/%d.png"
                        , zoom, x, y);

                if (!checkTileExists(x,y,zoom)) {
                    return null;
                }

                try {
                    return new URL(s);
                } catch (MalformedURLException e) {
                    throw new AssertionError(e);
                }
            }
        };

        //Add TileProvider to the map and get back the TileOverlay
        TileOverlay tileOverlay = map.addTileOverlay(new TileOverlayOptions()
        .tileProvider(tileProvider))
    }

    //Check that the tile server supports the required x, y, and zoom.
    //Complete this stub according to the tile range you support.
    //If you support a limited range of tiles at different zoom levels, then
    //you need to define the supported x, y range at each zoom level
    private boolean checkTileExists(int x, int y, int zoom) {
        int minZoom = 12;
        int maxZoom = 16;

        if ((zoom < minZoom || zoom > maxZoom)) {
            return false;
        }

        return true
    }

    private void setTransparencyForTileOverlay(MapView mapView, TileOverlay mTileOverlay) {

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                mTileOverlay = map.addTileOverlay(new TileOverlayOptions()
                        .tileProvider(new UrlTileProvider() {
                            @Override
                            public URL getTileUrl(int i, int i1, int i2) {
                                return null;
                            }
                        })
                        .transparency(0.5f));
            }
        });

        //Next call the updateTileOverlay method
        updateTileOverlay(mTileOverlay);
    }

    private void updateTileOverlay(TileOverlay mTileOverlay) {
        if (mTileOverlay != null) {
            //Switch between 0.0f and 0.5f transparency
            mTileOverlay.setTransparency(0.5f - mTileOverlay.getTransparency());
        }
    }

    private void fadeInAnimation(TileOverlayOptions tileOverlayOptions) {
        tileOverlayOptions.fadeIn(true);
    }

    private void removeATileOverlay(TileOverlay mTileOverlay) {
        mTileOverlay.remove();
    }

    private void clearStaleCache(TileOverlay mTileOverlay) {
        mTileOverlay.clearTileCache();
    }
}
