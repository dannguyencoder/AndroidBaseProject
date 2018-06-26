package com.example.vinhcrazyyyy.androidbaseproject.architecture_components.view_model;

import android.arch.lifecycle.ViewModel;

public class MyViewModel  extends ViewModel {
    private int mRotationCount = 0;

    public int getRotationCount() {
        mRotationCount = mRotationCount + 1;
        return mRotationCount;
    }
}
