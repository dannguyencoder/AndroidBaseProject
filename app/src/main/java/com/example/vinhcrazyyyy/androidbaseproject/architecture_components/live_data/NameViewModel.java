package com.example.vinhcrazyyyy.androidbaseproject.architecture_components.live_data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * 1. Create LiveData Object
 * */
public class NameViewModel extends ViewModel {

    //Create a LiveData with a String
    private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getmCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }
}
