package com.example.vinhcrazyyyy.androidbaseproject.firebase.auth;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginWithEmailPassword extends BaseFragment {

    FirebaseAuth auth;

    @Override
    protected void setup(View view) {
        //Get the Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            //go to main fragment/activity
            //close current fragment/activity
        }

        auth.signInWithEmailAndPassword("email", "password")
                .addOnCompleteListener(getActivity(),
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //If sign in fails, display a message to the user.
                                //If sign in succeeds the auth state listener will be notified
                                //and the logic to handle the signed in user can be handled
                                //in the listener

                                //progressBar.setVisibility(View.GONE)
                                if (!task.isSuccessful()) {
                                    //there was an error
                                    //check password length if it < 6 ==> show error
                                    //else show error message
                                } else { //sign in successful
                                    //go to main fragment/activity
                                    //close the current fragment/activity
                                }
                            }
                        })
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
