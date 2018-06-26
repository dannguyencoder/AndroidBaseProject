package com.example.vinhcrazyyyy.androidbaseproject.architecture_components.hanling_lifecycles;

import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

public class MyLifeCycleOwnerFragment extends BaseFragment {
    @Override
    protected void setup(View view) {
        //This is how you add LifeCycleObserver
        getLifecycle().addObserver(new MyLifeCycleObserver());
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
