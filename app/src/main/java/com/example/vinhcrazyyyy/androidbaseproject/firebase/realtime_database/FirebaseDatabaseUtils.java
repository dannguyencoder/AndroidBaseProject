package com.example.vinhcrazyyyy.androidbaseproject.firebase.realtime_database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vinhcrazyyyy.androidbaseproject.firebase.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDatabaseUtils {

    private void offlineData() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    //Performing CRUD operations
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private void insertData() {
        DatabaseReference mRef = mDatabase.getDatabase().getReference("copyright");
        mRef.setValue("@2018 vinhcrazyy. All rights reserved");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
        //Create new user node, which returns the unique key value
        //new user node would /users/$userid/
        String userId = mDatabase.push().getKey();

        //creating user object
        User user = new User("Vinh Nguyen", "dannguyencoder@gmail.com");

        //pushing user to 'users' node using the userId
        mDatabase.child(userId).setValue(user);
    }

    private void readData(String userId) {
        mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.d("TAG", "User name" + user.getName() + ", email " + user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Failed to read value
                Log.w("TAG", "Failed to read value ", databaseError.toException());
            }
        });
    }

    private void updateData(String userId) {
        String newEmail = "dannguyencoder@gmail.com";
        mDatabase.child(userId).child("email").setValue(newEmail);
    }

    private void deleteData() {
        mDatabase.removeValue();

        //or
        mDatabase.setValue(null);
    }

}
