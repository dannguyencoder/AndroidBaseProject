package com.example.vinhcrazyyyy.androidbaseproject.firebase.auth;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationFirebaseUtils {

    FirebaseAuth auth;

    public AuthenticationFirebaseUtils(FirebaseAuth auth) {
        this.auth = auth;
    }

    private void checkUserSession() {
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            //User is logged in
        }
    }

    private void changePassword(String newPassword) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //show password is updated message
                        } else {
                            //show failed password update message
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void changeEmail(String newEmail) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(newEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //show success update email message
                        } else {
                            //show error update email message
                        }
                    }
                });
    }

    private void deleteAcountUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //show success delete user
                            } else {
                                //show error delete user
                            }
                        }
                    });
        }
    }

    private void signOut() {
        auth.signOut();

        //this listener will be called when there is a change in the firebase session
        FirebaseAuth.AuthStateListener authStateListener = new AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user == null) {
                    //user auth state is changed - user is null
                    //launch the login activity/fragment
                    //close the current activity/fragment
                }
            }
        };
    }

    //activity lifecycle set-up
    //onResume: progressBar.setVisibility(View.GONE);
    //onStart: auth.addAuthStateListener(authListener);
    //onStop: if (authListener != null) auth.removeAuthStateListener(authListener);
}
