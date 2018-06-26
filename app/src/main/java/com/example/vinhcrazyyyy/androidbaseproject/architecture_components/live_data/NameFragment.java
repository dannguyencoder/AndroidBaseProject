package com.example.vinhcrazyyyy.androidbaseproject.architecture_components.live_data;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

/**
 * 2. Observe LiveData objects
 * */
public class NameFragment extends BaseFragment {

    //layout element
    //a TextView to show new updated name
    private TextView mNameTextView;

    //a Button to update the data in the model, hence automatically update the view
    // which is it is called (viewmodel)
    private Button mButton;

    //viewmodel
    private NameViewModel mModel;


    @Override
    protected void setup(View view) {

        //Get the ViewModel
        mModel = ViewModelProviders.of(this).get(NameViewModel.class);

        //Create the observer which updates the UI
        Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String newName) {
                //Update the UI, in this case, a TextView
                mNameTextView.setText(newName);
            }
        };

        //Observe the LiveData, passing in this activity/fragment
        //as the LifecycleOwner and the observer
        mModel.getmCurrentName().observe(this, nameObserver);

        updateLiveDataObjects();
    }

    private void updateLiveDataObjects() {
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String anotherName = "John Doe";
                mModel.getmCurrentName().setValue(anotherName);
                /**
                 * When we call setValue(anotherName), the value of the live data
                 * will be set. At this point, if there are active observers
                 * for our MutableLiveData<String> class and the LifeCycleOwner
                 * is in an ACTIVE state, then the value will be emitted to those observers
                 * and respective UI will be updated.
                 * We can setValue() only in MutableLiveData<T> class
                 * */
            }
        });
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
