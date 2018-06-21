package com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.models.Organization;
import com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.models.User;

/*Creating the database
Create a MyDatabase.java file and annotate your class with the
@Database decorator to declare your database.
It should contain both the name to be used for creating the table,
as well as the version number. */
// bump version number if your schema changes
@Database(entities={User.class, Organization.class}, version=1)
public abstract class MyDatabase extends RoomDatabase {
    // Declare your data access objects as abstract
    public abstract UserDao userDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}
/*Note: if you decide to change the schema for any tables you create later,
you will need to bump the version number. The version number should always be incremented
(and never downgraded) to avoid conflicts with older database versions.
Making schema changes will update the definitions in the app/schemas directory
as setup in the previous step. You will find the actual SQL definitions
to create these tables there. */