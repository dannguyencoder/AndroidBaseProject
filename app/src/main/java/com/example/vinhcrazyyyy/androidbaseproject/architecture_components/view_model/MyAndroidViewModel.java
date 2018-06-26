package com.example.vinhcrazyyyy.androidbaseproject.architecture_components.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * In some cases if you want context to get system service or some other stuff
 * you can use AndroidViewModel instead of ViewModel which will provide ou application context
 * */
public class MyAndroidViewModel extends AndroidViewModel {

    private int mRotationCount;
    private Context mContext;

    public MyAndroidViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
    }

    public int getRotationCount() {
        mRotationCount = mRotationCount + 1;
        return mRotationCount;
    }
}
