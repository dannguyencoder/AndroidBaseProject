package com.example.vinhcrazyyyy.androidbaseproject.google_maps;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class DrawingShapeOnMapUtils {

    private void drawingPolylinesOnMap(GoogleMap myMap) {
        //Instantiates a new Polyline object and add points to define rectangle
        PolylineOptions recOptions = new PolylineOptions()
                .add(new LatLng(37.35, -122.0))
                .add(new LatLng(37.45, -122.0)) //North of the previous point, but at the same longitude
                .add(new LatLng(37.45, -122.2)) //Same latitude, and 30km to the west
                .add(new LatLng(37.35, -122.0)) //Same longitude, and 16km to the south
                .add(new LatLng(37.35, -122.0));//Closes the polyline

        //Get back the mutable poyline
        Polyline polyline = myMap.addPolyline(recOptions);
    }

    private void drawingPolygonsOnMap(GoogleMap myMap) {
        //Instantiates a new Polygon object and add points to define rectangle
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(37.35, -122.0),
                        new LatLng(37.45, -122.0),
                        new LatLng(37.45, -122.2),
                        new LatLng(36.35, -122.2),
                        new LatLng(37.35, -122.0))
                .strokeColor(Color.RED).fillColor(Color.BLUE);

        //Get back the mutable Polygon
        Polygon polygon = myMap.addPolygon(rectOptions);
    }

    private void drawingCirclesOnMap(GoogleMap myMap) {
        //Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(37.4, -122.1))
                .radius(1000); //In meters

        //Get back the mutable circle
        Circle circle = myMap.addCircle(circleOptions);
    }
}
