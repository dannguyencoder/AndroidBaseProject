package com.example.vinhcrazyyyy.androidbaseproject.architecture_components.view_model;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.view.View;
import android.widget.TextView;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

public class RotationFragment extends BaseFragment {
    @Override
    protected void setup(View view) {
        TextView txtRotation = view.findViewById(R.id.txtRotate);

        /**
         * If you don't find ViewModelProviders, put both these into build.gradle
         * implementation "android.arch.lifecycle:extensions:1.1.0"
            implementation "android.arch.lifecycle:viewmodel:1.1.0"
         * */
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        txtRotation.setText("Rotations : " + myViewModel.getRotationCount());
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
