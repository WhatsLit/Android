package com.whatslit.whatslit;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        //TODO: move camera to user's location

        //TODO: get locations/scores from server dynamically and put markers on map

        //TODO: setup listeners

    }


    //The Event class stores an event. The purpose of this
    //class is to organize events easier
    public class Event{

        private double lat;
        private double lon;
        private int rating;
        private String eventType;
        private More more;


        //Creates a new Event, with a given location, rating, event type, and more info
        public Event(double la, double lo, int r, String e, More m){

            lat=la;
            lon=lo;
            rating=r;
            eventType=e;
            more=m;
        }


    }

    //The More class stores the further information associated
    //with an event.The purpose of this class is to store further
    //information in case we want to show this info in a new window.
    public class More{

        private String eventName;
        private String hostName;
        private String description;
        private String[] comments;

        public More(String e, String h, String d, String[] c){

            eventName=e;
            hostName=h;
            description=d;

            comments=new String[c.length];

            for(int i=0; i<c.length; i++){

                comments[i]=c[i];
            }
        }


    }
}
