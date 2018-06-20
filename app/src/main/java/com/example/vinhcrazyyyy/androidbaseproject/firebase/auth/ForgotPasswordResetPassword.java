package com.example.vinhcrazyyyy.androidbaseproject.firebase.auth;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordResetPassword extends BaseFragment {

    FirebaseAuth auth;

    @Override
    protected void setup(View view) {
        auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail("email")
                .addOnCompleteListener(getActivity(),
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //show message
                                } else {
                                    //show error message
                                }

                                //progressBar.setVisibility(View.GONE)
                            }
                        });
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
