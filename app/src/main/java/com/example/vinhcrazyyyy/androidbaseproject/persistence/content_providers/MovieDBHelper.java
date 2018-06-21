package com.example.vinhcrazyyyy.androidbaseproject.persistence.content_providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*SQLiteOpenHelper
    Now that we've defined what our database will look like,
    we need to actually create the database. To do this, we use the SQLiteOpenHelper class.
    We can start by creating the constructor of the class,
    which calls the base class using the database version
    (which will be incremented every time the database changes),
    and the name of the database:*/
public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieList.db";

    public MovieDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*There are two required implementations for this class: onCreate() and onUpgrade().
    These are fairly self explanatory, the first is called when the database is created,
    and the second is called anytime DATABASE_VERSION is incremented.
    For this example, we will not handle updating the database:*/

    /**
     * Called when the database is first created.
     * @param db The database being created, which all SQL statements will be executed on.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        addGenreTable(db);
        addMovieTable(db);
    }

    /**
     * Called whenever DATABASE_VERSION is incremented.
     * This is used whenever schema changes need
     * to be made or new tables are added.
     * @param db The database being updated.
     * @param oldVersion The previous version of the database. Used to determine whether or not
     *                   certain updates should be run.
     * @param newVersion The new version of the database.
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /*Lastly, we will write the SQL code to create the database tables.
    Since this is not a SQL tutorial, I won't give them much explanation:*/
    /**
     * Inserts the genre table into the database.
     * @param db The SQLiteDatabase the table is being inserted into.

     */
    private void addGenreTable(SQLiteDatabase db){
        db.execSQL(
                "CREATE TABLE " + MovieContract.GenreEntry.TABLE_NAME + " (" +
                        MovieContract.GenreEntry._ID + " INTEGER PRIMARY KEY, " +
                        MovieContract.GenreEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL);"
        );
    }

    /**
     * Inserts the movie table into the database.
     * @param db The SQLiteDatabase the table is being inserted into.
     */
    private void addMovieTable(SQLiteDatabase db){
        db.execSQL(
                "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                        MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY, " +
                        MovieContract.MovieEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_GENRE + " INTEGER NOT NULL, " +
                        "FOREIGN KEY (" + MovieContract.MovieEntry.COLUMN_GENRE + ") " +
                        "REFERENCES " + MovieContract.GenreEntry.TABLE_NAME + " ("
                        + MovieContract.GenreEntry._ID + "));"
        );
    }
}
