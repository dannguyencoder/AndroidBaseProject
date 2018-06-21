package com.example.vinhcrazyyyy.androidbaseproject.persistence.sqlite;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

import java.util.List;

//Using our Database Handler
//We can now leverage our database handler and models to persist data to our SQLite store
public class SQLiteExampleFragment extends BaseFragment {
    @Override
    protected void setup(View view) {
        //Create sample data
        User sampleUser = new User();
        sampleUser.userName = "Vinh";
        sampleUser.profilePictureURL = "https://i.imgur.com/tGbaZCY.jpg";

        Post samplePost = new Post();
        samplePost.user = sampleUser;
        samplePost.text = "Won won!";

        //Get singleton instance of database
        PostDatabaseHelper databaseHelper = PostDatabaseHelper.getInstance(getContext());

        //Add sample post to the database
        databaseHelper.addPost(samplePost);

        //Get all posts from database
        List<Post> posts = databaseHelper.getAllPosts();
        for (Post post : posts) {
            //do something
            Log.d("TAG", "post in the database: " + post.text);
            System.out.println("post in the database: " + post.text);
            Toast.makeText(getContext(), post.text, Toast.LENGTH_SHORT).show();
        }
    }
    //In many cases, rather than interacting with SQL directly, Android apps can leverage
    //one of the many available "high-level ORMs" to
    // persist Java models to a database table instead

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
