package com.whatslit.whatslit;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.*;
import android.content.Intent;
import org.json.*;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ArrayList<Event> eventsInArea;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras=getIntent().getExtras();
        String username=extras.getString("username");
        String token=extras.getString("token");
        currentUser=new User(username, token);

        //TODO: load events from server and update eventsInArea

        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        //TODO: set up markers for buttons
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

        //TODO: check shit from server every minute
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

        for(Event next:eventsInArea){


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

        //TODO: put markers on map for each event

        //TODO: setup listeners for markers

    }


    //The Event class stores an event. The purpose of this
    //class is to organize events easier
    public class Event{

        public static final String rating_key="Rating";
        public static final String event_type_key="Event Type";
        public static final String comments_key="Comments";
        public static final String description_key="Description";

        private int ID;
        private double lat;
        private double lon;
        private int rating;
        private String eventType;
        private int updateCount; //updates whenever any event instances are changed
        private long timePosted;
        private int numComments;
        private String description;
        private int creatorID;
        private String creatorUsername;
        private ArrayList<Comment> comments;

        //Creates a new Event
        public Event(int i, double la, double lo, int r, String e, long t, int n, String d, int c, String cu){

            ID=i;
            lat=la;
            lon=lo;
            rating=r;
            eventType=e;
            updateCount=0;
            timePosted=t; //in milliseconds
            numComments=n;
            comments=new ArrayList<Comment>(numComments); //initialized empty
            description=d;
            creatorID=c; //-1 if posted anonymously
            creatorUsername=cu; //null if posted anonymously

            //TODO: send this info to server

            //TODO: update view with new event
            //this involves creating a new marker and listener for this marker
        }

        //begin accessors
        public int getID(){

            return ID;
        }

        public double getLat(){

            return lat;
        }

        public double getLon(){

            return lon;
        }

        public int getRating(){

            return rating;
        }

        public String getEventType(){

            return eventType;
        }

        public int getUpdateCount(){

            return updateCount;
        }

        public long getTimePosted(){

            return timePosted;
        }

        public int getNumComments(){

            return numComments;
        }

        public String getDescription(){

            return description;
        }

        public int getCreatorID(){

            return creatorID;
        }

        public String getCreatorUsername(){

            return creatorUsername;
        }

        public ArrayList<Comment> getComments(){

            return comments;
        }
        //end accessors

        //returns the number of seconds since the event was posted
        public int secondsSincePosted(){

            return (int)((System.currentTimeMillis()-timePosted)/1000);
        }

        //for adding comments the first time comments are retrieved from the server
        public void storeComments(Comment[] toAdd){

            for (Comment current: toAdd){

                comments.add(current);
            }
        }

        //begin mutators
        public void addComment(Comment toAdd){

            comments.add(toAdd);
            numComments+=1;

            updateSomeAttribute(comments_key);
        }

        //votes an event up or down
        public void vote(boolean up){

            if (up)
                rating++;

            else
                rating--;

            updateSomeAttribute(rating_key);
        }

        public void changeEventType(String newEventType){

            eventType=newEventType;
            updateSomeAttribute(event_type_key);
        }

        public void changeDescription(String newDescription){

            description=newDescription;
            updateSomeAttribute(description_key);
        }
        //end mutators

        //updates immediately on current view, and sends update to server so other users
        //can receive this update
        private void updateSomeAttribute(String attributeName){

            updateCount++;

            //TODO: send update to server
            //TODO: update view
        }
    }

    //Comment class--each event will have arraylist of comments
    public class Comment{

        private String text;
        private long timePosted;
        private int posterID;
        private String posterUsername;
        private int rating;

    }

    //User class
    public class User{

        private String username;
        private String token;

        public User(String u, String t){

            username=u;
            token=t;
        }

    }
}
