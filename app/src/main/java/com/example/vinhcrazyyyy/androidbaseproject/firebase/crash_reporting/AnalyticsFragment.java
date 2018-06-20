package com.example.vinhcrazyyyy.androidbaseproject.firebase.crash_reporting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

import java.util.Random;

public class AnalyticsFragment extends BaseFragment{

    private FirebaseAnalytics firebaseAnalytics;
    String[] foods;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        foods = new String[]{"Apple", "Banana", "Grape", "Mango", "Orange"};

        //Obtain the Firebase Analytics instance.
        //After that we can set up a few events
        firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());

        Food food = new Food();
        food.setId(1);

        //choose random food name from the list
        food.setName(foods[randomIndex()]);

        //create a bundle and pass it to the logEvent() method
        Bundle bundle = new Bundle();
        bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, food.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, food.getName());

        //Logs an app event
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Sets whether analytics collection is enabled for this app on this device
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);

        //Sets the minimum engagement time required before starting a session.
        //The default value is 1000 (10 seconds). Let's make it 20 seconds
        //just for fun
        firebaseAnalytics.setMinimumSessionDuration(20000);

        //Sets the duration of inactivity that terminates the current session.
        //The default value is 180000 (30 minutes)
        firebaseAnalytics.setSessionTimeoutDuration(500);

        //Sets the user ID property
        firebaseAnalytics.setUserId(String.valueOf(food.getId()));

        //Sets a user property to a given value
        firebaseAnalytics.setUserProperty("Food", food.getName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private int randomIndex() {
        int min = 0;
        int max = foods.length - 1;
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }

    @Override
    protected void setup(View view) {

    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
