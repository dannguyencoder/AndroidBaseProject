package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.vinhcrazyyyy.androidbaseproject.server_synchronization.ArticleContract.Articles;

/*Step 1: The Content Provider
 * Now that we've created the neccessary components to implement a ContentProvider,
 * we can start.
 * We need to create a ContentProvider for our hypothetical example to provide access
 * to our local data source.
 * This ContentProvider is absolutely imperative as we use it to synchronize local data changes
 * and utilize its content observer to potentially update the UI after syncing.
 * For an in-depth tutorial on creating a ContentProvider, refer to this fantastic article.
 * http://guides.codepath.com/android/Creating-Content-Providers*/

/*This is the ContentProvider that will be used by our SyncAdapter
 * to sync local data*/
public class ArticleProvider extends ContentProvider {

    //Use ints to represent different queries
    private static final int ARTICLE = 1;
    private static final int ARTICLE_ID = 2;

    private static final UriMatcher uriMatcher;

    static {
        //Add all our query types to our UriMatcher
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ArticleContract.CONTENT_AUTHORITY, ArticleContract.PATH_ARTICLES, ARTICLE);
        uriMatcher.addURI(ArticleContract.CONTENT_AUTHORITY, ArticleContract.PATH_ARTICLES, ARTICLE_ID);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        this.db = DatabaseClient.getInstance(getContext()).getDb();
        return false;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        //Find the MIME type of the results...multiple results or a single result
        switch (uriMatcher.match(uri)) {
            case ARTICLE:
                return ArticleContract.Articles.CONTENT_TYPE;
            case ARTICLE_ID:
                return ArticleContract.Articles.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Invalid URI!");
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor c;
        switch (uriMatcher.match(uri)) {
            //Query for multiple article results
            case ARTICLE:
                c = db.query(ArticleContract.Articles.NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            //Query for single article result
            case ARTICLE_ID:
                long _id = ContentUris.parseId(uri);
                c = db.query(ArticleContract.Articles.NAME,
                        projection,
                        ArticleContract.Articles.COL_ID + "=?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI!");
        }

        //Tell the cursor to register a content observer to observe changes to the URI
        //or its descendants.
        assert getContext() != null;
        c.setNotificationUri(getContext().getContentResolver(), null);
        return c;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri returnUri;
        long _id;

        switch (uriMatcher.match(uri)) {
            case ARTICLE:
                _id = db.insert(ArticleContract.Articles.NAME, null, values);
                returnUri = ContentUris.withAppendedId(ArticleContract.Articles.CONTENT_URI, _id);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI!");
        }

        //Notify any observers to update thr UI
        assert getContext() != null;
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rows;
        switch (uriMatcher.match(uri)) {
            case ARTICLE:
                rows = db.update(ArticleContract.Articles.NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI");
        }

        if (rows != 0) {
            assert getContext() != null;
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rows;
        switch (uriMatcher.match(uri)) {
            case ARTICLE:
                rows = db.delete(ArticleContract.Articles.NAME, selection, selectionArgs);
                break;
                default: throw new IllegalArgumentException("Invalid URI");
        }

        //Notify any observers to update the UI
        if (rows != 0) {
            assert getContext() != null;
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }
}
/*Declare the ContentProvider
* In order to use our ContentProvider, we need to declare it in the AndroidManifest.xml file.
* It is important to note that:
* 1. "android:authorities" must match our ContentProvider's authority exactly, and
* 2. "android:syncable" must be true
*
* <provider
*   android:name = ".com.example.vinhcrazyyyy.ArticleProvider
*   android:authorities = "com.example.sync"
*   android:exported = "false"
*   android:syncable = "true"/>*/
