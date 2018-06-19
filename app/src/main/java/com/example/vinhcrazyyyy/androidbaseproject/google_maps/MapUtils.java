package com.example.vinhcrazyyyy.androidbaseproject.google_maps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.Toast;

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

public class MapUtils {

    private Context context;

    public MapUtils(Context context) {
        this.context = context;
    }

    public void controlCamera(GoogleMap map, double latitude, double longtitude) {
        LatLng latLng  = new LatLng(latitude, longtitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        map.animateCamera(cameraUpdate);
    }

    public void addMarkers(GoogleMap map, LatLng position, BitmapDescriptor icon, String title, String snippet) {
        //Set the color of the marker to green
        BitmapDescriptor defaultMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

        //listingPostion is LatLng point
        LatLng listingPosition = new LatLng(-33.867, 151.206);

        //Create the marker on the fragment
        Marker mapMarker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .snippet(snippet)
                .icon(icon));
    }

    public void attachClickHandlerToMarker(GoogleMap map) {
        if (map!= null) {
            //Attach marker click listener to map here
            map.setOnMarkerClickListener(new OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    //Handle marker click here
                    return false;
                }
            });
        }
    }

    //use this to map each marker's id to the associated data model
    public void associateMarkersToModels(GoogleMap map) {
        final Map<Marker, MyModelForMarker> markerMap = new HashMap<>();

        MarkerOptions opt = new MarkerOptions();
        //fill out opt from MyModelForMarker
        Marker marker = map.addMarker(opt);

        //create model for marker
        MyModelForMarker myModelForMarker = new MyModelForMarker();

        markerMap.put(marker, myModelForMarker);

        map.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //pull the model of the HashMap using clicked Marker
                //There is also method Marker.getId() that return a string of the marker
                //Update: after 4 years, Google have added method Marker.setTag(Object tag)
                //to associate arbitrary data with a marker
                MyModelForMarker modelForMarker = markerMap.get(marker);
                //do something with the model
                return false;
            }
        });
    }

    public BitmapDescriptor createCustomMarkerDrawable(@DrawableRes int drawableResId) {
        BitmapDescriptor customMarker = BitmapDescriptorFactory.fromResource(drawableResId);
        return customMarker;
    }

    public BitmapDescriptor createSpeechBubble(GoogleMap map, String title) {
        //use the IconGenerator class included in the MapUtils library to set the color
        //and text of this speech bubble
        IconGenerator iconGenerator = new IconGenerator(getContext());

        //Possible color options
        //STYLE_WHITE, STYLE_RED, STYLE_BLUE, STYLE_GREEN, STYLE_PURPLE, STYLE_ORANGE
        iconGenerator.setStyle(IconGenerator.STYLE_GREEN);
        //Swap text here to live inside speech bubble
        Bitmap bitmap = iconGenerator.makeIcon(title);
        //Use BitmapDescriptor to create the marker
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmap);

        //add this icon to the map (replace the default one)
        Marker mapMarker = map.addMarker(new MarkerOptions()
                //add options here
                .icon(icon));

        return icon;
    }

    public void enableMarkerToBeDraggable(GoogleMap map, Marker marker) {
        map.setOnMarkerDragListener(new OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });

        //remember to set marker draggable to true
        marker.setDraggable(true);
    }

    public void showAlertDialogOnLongClick(final GoogleMap map) {
        map.setOnMapLongClickListener(new OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {
                Toast.makeText(getContext(), "Long press", Toast.LENGTH_SHORT).show();

                showAlertDialogForPoint(map, point, R.layout.dialog_layout);
            }
        });
    }

    private void showAlertDialogForPoint(final GoogleMap map, final LatLng point, @LayoutRes int dialogLayoutId) {
        //Inflate dialog_layout.xml to view
        View dialogView = LayoutInflater.from(getContext()).inflate(dialogLayoutId, null);

        //Create alert dialog builder
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        //set dialog_layout.xml to AlertDialog builder
        alertDialogBuilder.setView(dialogView);

        //create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        //Configure dialog button (OK)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Define color of marker icon
                        BitmapDescriptor defaultMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                        //Extract content from alert dialog
                        String title = ((EditText) (alertDialog.findViewById(R.id.etTitle))).getText().toString();
                        String snippet = ((EditText) (alertDialog.findViewById(R.id.etSnippet))).getText().toString();
                        //Creates and adds marker to the map
                        Marker marker = map.addMarker(new MarkerOptions()
                                .position(point)
                                .title(title)
                                .snippet(snippet)
                                .icon(defaultMarker));
                    }
                });

        //Configure dialog button (Cancel)
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do something or not
                    }
                });

        //Dislay the dialog
        alertDialog.show();
    }

    public void dropPinEffect(final Marker marker) {
        //Handler allows us to repeat a code block after a specified delay
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final long duration = 1500;

        //Use the bounce interpolator
        final Interpolator interpolator = new BounceInterpolator();

        //Animate marker with a bounce updating its position every 15ms
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                //Calculate t for bounce based on elapsed time
                float t = Math.max(1 - interpolator.getInterpolation((float) elapsed/duration), 0);
                //Set the anchor
                marker.setAnchor(0.5f, 1.0f + 14 * t);

                if (t > 0.0) {
                    //Post this event again 15ms from now
                    handler.postDelayed(this, 15);
                } else { //done elapsing, show window
                    marker.showInfoWindow();
                }
            }
        });
    }

    //Display the alert dialog that add the marker
    private void showAlertDialogForPoint(GoogleMap map, final LatLng point) {
        //build the alert dialog
        final AlertDialog alertDialog = null;

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Define color of marker icon
                        BitmapDescriptor defaultMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                        //Extract content from alert dialog
                        String title = ((EditText) alertDialog.findViewById(R.id.etTitle)).getText().toString();
                        String snippet = ((EditText) alertDialog.findViewById(R.id.etSnippet)).getText().toString();

                        //Creates and add marker to the map
                        Marker marker = map.addMarker(new MarkerOptions()
                                .position(point)
                                .title(title)
                                .snippet(snippet)
                                .icon(defaultMarker));

                        //Animate marker using drop effect
                        // --> Call the dropPinEffect method here
                        dropPinEffect(marker);
                    }
                });
    }

    //Use this if you want to use your own layout instead of default option
    private void setCustomInfoWindow(GoogleMap map) {
        map.setInfoWindowAdapter(new CustomWindowAdapter(getLayoutInflater()));
    }

    //Loading remote images into InfoWindow Contents
    //If you are loading remote images into a InfoWindow, there is a common problem
    //where the image doesn't load properly the first time when you open the window
    //for each item. The hacky solution is to "refresh the window after a delay"
    //The better solution is to
    //trigger the window  to "refresh after the image is finished downloading"
    private void setOnMarkerClickAfterDelayToLoadImage(final GoogleMap map) {
        //first solution: refresh window after a delay
        map.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                //Load image to ImageView with Picasso.with(image_view)
                //in the InfoWindowAdapter
                marker.showInfoWindow();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        marker.showInfoWindow();
                    }
                }, 200);
                return true;
            }
        });

        //second: refesh after the image is finished downloading
        map.setInfoWindowAdapter(new InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                //inflate the window
                //set other views content

                //set image view like this
                //Be careful: this is just a demo.
                //Remember to delcare the not_first_time_showing_info_window
                // as a global variable !!!
                //If not then the Picasso callback will call showInfoWindow()
                // that will recall the callback, which recall showInfoIndow()...
                //lead to indefinite recursion's going
                boolean not_first_time_showing_info_window = false;
                if (not_first_time_showing_info_window) {
                    Picasso.get().load("an image url").into(target_image_view);
                } else { //if it is the first time, load the image with the callback set
                    not_first_time_showing_info_window = true;
                    Picasso.get().load("an image url").into(target_image_view, new InfoWindowRefresher(marker));
                }
                return v;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }

    private void changeMapType(MapView mapView) {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                //Once map is loaded
                //Support types include: MAP_TYPE_NORMAL, MAP_TYPE_SATELLITE
                //MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
    }

    private void useVectorAsMapMarkers(GoogleMap map) {
        //First install the com.google.maps.android:android-maps-utils library

        //1. get the marker
        Bitmap vectorBitmap = getMarkerVectorBitmap();

        //2. Comsume the Bitmap in your Marker Creator
        Marker mapMarker = map.addMarker(new MarkerOptions()
        .position(new LatLng(0123,345))
        .icon(BitmapDescriptorFactory.fromBitmap(vectorBitmap)));
    }

    public Bitmap getMarkerVectorBitmap() {
        IconGenerator iconGen = new IconGenerator(context);

        //Define the size you want from dimension file
        int shapSize = context.getResources().getDimensionPixelSize(R.dimen.custom_marker_size);

        Drawable shapDrawble = ResourcesCompat.getDrawable(context.getResources(),
                R.drawable.drawble_custom_marker, null);
        iconGen.setBackground(shapDrawble);

        //Create a view container to set the size
        View view = new View(context);
        view.setLayoutParams(new LayoutParams(shapSize, shapSize));
        iconGen.setContentView(view);

        //Create the bitmap
        Bitmap bitmapVectorMarker = iconGen.makeIcon();
        return bitmapVectorMarker;
    }
}
