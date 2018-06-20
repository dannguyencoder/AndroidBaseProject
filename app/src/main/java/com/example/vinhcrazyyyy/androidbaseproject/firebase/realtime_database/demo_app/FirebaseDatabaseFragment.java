package com.example.vinhcrazyyyy.androidbaseproject.firebase.realtime_database.demo_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;
import com.example.vinhcrazyyyy.androidbaseproject.firebase.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDatabaseFragment extends BaseFragment {

    private static final String TAG = FirebaseDatabaseFragment.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName, inputEmail;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Displaying toolbar icon
        txtDetails = getView().findViewById(R.id.txt_user);
        inputName  = getView().findViewById(R.id.name);
        inputEmail = getView().findViewById(R.id.email);
        btnSave = getView().findViewById(R.id.btn_save);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        //get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        //store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        //app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                //set value somewhere
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Failed to read value
                Log.e(TAG, "Failed to read app title value", databaseError.toException() );
            }
        });

        //Save/update the user
        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();

                //Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(name, email);
                } else {
                    updateUser(name, email);
                }
            }
        });

        toggleButton();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void updateUser(String name, String email) {
        //updating the user via child nodes
        if (!TextUtils.isEmpty(name)) {
            mFirebaseDatabase.child(userId).child("name").setValue(name);
        }

        if (!TextUtils.isEmpty(email)) {
            mFirebaseDatabase.child(userId).child("email").setValue(email);
        }
    }

    //Changing button text
    private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            btnSave.setText("Save");
        } else {
            btnSave.setText("Update");
        }
    }

    //Create new user node under 'users'
    private void createUser(String name, String email) {
        //TODO
        //In real apps this userId should fetched
        //by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        User user = new User(name, email);
        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }

    private void addUserChangeListener() {
        //User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                //Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.name + ", " + user.email);

                //Dislay newly updated name and email
                txtDetails.setText(user.name + " , " + user.email);

                //clear edit text
                inputEmail.setText("");
                inputName.setText("");

                toggleButton();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Failed to read value
                Log.e(TAG, "Failed to read user", databaseError.toException());
            }
        });
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_firebase_database;
    }
}
