package com.example.vinhcrazyyyy.androidbaseproject.persistence.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceUtils {

    Context context;

    public SharedPreferenceUtils(Context context) {
        this.context = context;
    }

    private void storeDataWithSharedPreference() {
        //Specifying a Preference file
        //"Settings" is the name of the preference file. If it does not exist, it will be created
        //The mode value of 0 designates the default behaviour, which is to allow access
        //to only to the application. There are other read/write permissions that can be specified
        //but are no longer encouraged for "security reasons - MODE WORLD READABLE"
        SharedPreferences mSettings = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        //Or Using a default Preference file
        //If you wish to have common preference file and don't wish to specify a file,
        //you can use the default shared preference, too
        //Using this way will default the preference file to be stored as
        ///data/data/com.package.name/shared_prefs/com.package.name_prefernces.xml
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);

        //Editing Preferences
        //The next step is to create an Editor instance of SharedPreferences like so
        SharedPreferences.Editor editor = mSettings.edit();
        //Then you can begin to add data to the Settings file declared when you instantiate
        //the SharedPreferences
        int id = 1;
        String username = "john";
        editor.putString("username", username);
        editor.putInt("id", id);

        //once you ware finished adding data you need to apply() the edits by calling
        editor.apply();
        //that's the last step. Your data is stored and you can access your data using the method below
    }

    private void accessStoredDataFromSharedPreferences() {
        //First instantiate an instance of your shared preferences
        SharedPreferences mSettings = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        //access data
        String cookieName = mSettings.getString("cookieName", "missing");
        //This will either grab the value that was previously set with the key of "cookieName"
        //or return the string "missing" if it is not found
        //That's all there to it.
    }
}
