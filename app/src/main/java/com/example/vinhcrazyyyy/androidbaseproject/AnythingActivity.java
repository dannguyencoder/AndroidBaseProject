package com.example.vinhcrazyyyy.androidbaseproject;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vinhcrazyyyy.androidbaseproject.firebase.realtime_database.demo_app.FirebaseDatabaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.viewpager_tabs.TabsFragment;

public class AnythingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anything_activity);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new FirebaseDatabaseFragment());
        fragmentTransaction.commit();
    }
}
