package com.example.vinhcrazyyyy.androidbaseproject;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.MyDatabase;

public class MyApplication extends Application {

    /*Instantiating Room
    Next, we need to instantiate Room in a custom application class.
    If you do not have an Application object, create one in MyApplication.java
    as shown below:*/

    MyDatabase myDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        public class RestClientApp extends Application {
            // when upgrading versions, kill the original tables by using fallbackToDestructiveMigration()
            myDatabase = Room.databaseBuilder(this, MyDatabase.class, MyDatabase.NAME).fallbackToDestructiveMigration().build();
        }

        public MyDatabase getMyDatabase() {
            return myDatabase;
        }
    }
}
