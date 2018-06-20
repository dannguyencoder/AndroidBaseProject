package com.example.vinhcrazyyyy.androidbaseproject.firebase.auth;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpWithEmailPassword extends BaseFragment {

    private FirebaseAuth auth;

    @Override
    protected void setup(View view) {
        auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword("email", "password")
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getContext(), "createUserWithEmail:onCompelete " + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.GONE);

                        //If sign in fails, display a message to the user. If sign in succeeds
                        //the auth state listener will be notified and logic to handle the
                        //signed in user can be handled in the listener
                        if (!task.isSuccessful()) {
                            Toast.makeText(getContext(), "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            //go to main activity/fragment
                            //close the current activity/fragment
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_sign_up_firebase_auth;
    }
}
