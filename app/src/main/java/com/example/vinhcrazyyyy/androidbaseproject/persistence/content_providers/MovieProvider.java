package com.example.vinhcrazyyyy.androidbaseproject.persistence.content_providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

//ContentProvider
/*Now that we have covered everything necessary to setup our provider,
let's discuss how to actually write the ContentProvider class.
I'll break down step by step how to complete this class.
*/
public class MovieProvider extends ContentProvider {
    /*First, we need to define an integer identifier for each URI
    or query we plan to write. In this case we will have two for each query;
    A URI for all rows,
    and a URI for an individual row:*/
    // Use an int for each URI we will run, this represents the different queries
    private static final int GENRE = 100;
    private static final int GENRE_ID = 101;
    private static final int MOVIE = 200;
    private static final int MOVIE_ID = 201;

    /*Next, we can define our other class variables such as our SQLiteOpenHelper
    which is used to access the database itself, and a URIMatcher that will take in a URI
    and match it to the appropriate integer identifier we just defined:*/
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private MovieDBHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new MovieDBHelper(getContext());
        return true;
    }

    /**
     * Builds a UriMatcher that is used to determine witch database request is being made.
     */
    public static UriMatcher buildUriMatcher(){
        String content = MovieContract.CONTENT_AUTHORITY;

        // All paths to the UriMatcher have a corresponding code to return
        // when a match is found (the ints above).
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(content, MovieContract.PATH_GENRE, GENRE);
        matcher.addURI(content, MovieContract.PATH_GENRE + "/#", GENRE_ID);
        matcher.addURI(content, MovieContract.PATH_MOVIE, MOVIE);
        matcher.addURI(content, MovieContract.PATH_MOVIE + "/#", MOVIE_ID);

        return matcher;

        /*The main thing to notice here is that if we see a URI that ends with a given path,
        it will match with the URI for that table, but if an id is appended to the path
        we are looking for a row with that id.*/
    }

    /*getType
    The getType method is used to find the MIME type of the results,
    either a directory of multiple results, or an individual item:*/
    @Override
    public String getType(Uri uri) {
        switch(sUriMatcher.match(uri)){
            case GENRE:
                return MovieContract.GenreEntry.CONTENT_TYPE;
            case GENRE_ID:
                return MovieContract.GenreEntry.CONTENT_ITEM_TYPE;
            case MOVIE:
                return MovieContract.MovieEntry.CONTENT_TYPE;
            case MOVIE_ID:
                return MovieContract.MovieEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    /*query
    The query method takes in five parameters:

        uri: The URI (or table) that should be queried.
        projection: A string array of columns that will be returned in the result set.
        selection: A string defining the criteria for results to be returned.
        selectionArgs: Arguments to the above criteria that rows will be checked against.
        sortOrder: A string of the column(s) and order to sort the result set by.

    In order to query the database, we will switch based on the matched URI integer
    and query the appropriate table as necessary.*/
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor retCursor;

        switch(sUriMatcher.match(uri)){

            case GENRE:
                retCursor = db.query(
                        MovieContract.GenreEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case GENRE_ID:
                long _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        MovieContract.GenreEntry.TABLE_NAME,
                        projection,
                        MovieContract.GenreEntry._ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            case MOVIE:
                retCursor = db.query(
                        MovieContract.MovieEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case MOVIE_ID:
                _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        MovieContract.MovieEntry.TABLE_NAME,
                        projection,
                        MovieContract.MovieEntry._ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Set the notification URI for the cursor to the one passed into the function.
        // This
        // causes the cursor to register a content observer to watch for changes that happen
        // to
        // this URI and any of it's descendants. By descendants, we mean any URI that begins
        // with this path.
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    /*insert
    The insert method takes in a ContentValues object,
    which is a key value pair of column names and values to be inserted.
    Similar to the query method, and the update and delete methods that follow,
    we will switch based on the URI and act on the appropriate table:*/
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch(sUriMatcher.match(uri)){
            case GENRE:
                _id = db.insert(MovieContract.GenreEntry.TABLE_NAME,
                        null, values);
                if(_id > 0){
                    returnUri =  MovieContract.GenreEntry.buildGenreUri(_id);
                } else{
                    throw new UnsupportedOperationException("Unable to insert rows into: "
                            + uri);
                }
                break;
            case MOVIE:
                _id = db.insert(MovieContract.MovieEntry.TABLE_NAME,
                        null, values);
                if(_id > 0){
                    returnUri = MovieContract.MovieEntry.buildMovieUri(_id);
                } else{
                    throw new UnsupportedOperationException("Unable to insert rows into: "
                            + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Use this on the URI passed into the function to notify any observers
        // that the uri has
        // changed.
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    /*update and delete
    The update and delete methods take in a selection string and arguments
    to define which rows should be updated or deleted.
    They differ in that the update method requires a ContentProvider object as well,
    for the columns in that row(s) that will be updated.*/
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows; // Number of rows effected

        switch(sUriMatcher.match(uri)){
            case GENRE:
                rows = db.delete(MovieContract.GenreEntry.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case MOVIE:
                rows = db.delete(MovieContract.MovieEntry.TABLE_NAME,
                        selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Because null could delete all rows:
        if(selection == null || rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows;

        switch(sUriMatcher.match(uri)){
            case GENRE:
                rows = db.update(MovieContract.GenreEntry.TABLE_NAME,
                        values, selection, selectionArgs);
                break;
            case MOVIE:
                rows = db.update(MovieContract.MovieEntry.TABLE_NAME,
                        values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }

    /*Using the Content Provider
    * In order to use the content provider, even from within your own app,
    * you must update the AndroidManifest.xml file: In the application node, add:
    * <provider

          android:name=".MovieProvider"

          android:authorities="com.androidessence.moviedatabase"

          android:exported="false"

          android:protectionLevel="signature"

          android:syncable="true"/>
    * */
}
