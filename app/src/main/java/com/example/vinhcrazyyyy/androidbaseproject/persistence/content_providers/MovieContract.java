package com.example.vinhcrazyyyy.androidbaseproject.persistence.content_providers;

//Content providers are Android’s central mechanism that enables you to
// access data of other applications – mostly information stored in databases
// or flat files. As such content providers are one of Android’s central component types
// to support the modular approach common to Android. Without content providers accessing data
// of other apps would be a mess.
//
//Content providers support the four basic operations, normally called CRUD-operations.
// CRUD is the acronym for create, read, update and delete. With content providers
// those objects simply represent data – most often a record (tuple) of a database –
// but they could also be a photo on your SD-card or a video on the web.
// Let's take a look at how to create a content provider.

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

//Contract Classes
//Before creating your ContentProvider, it is a good idea to create contract classes
// that define your database schema. Let's consider creating a movie database.
// Inside of our MovieContract class, we need to define a few properties:
// a content authority, which is a unique identifier for our database, a base URI,
// and path names for each table:
public class MovieContract {
    /**
     * The Content Authority is a name for the entire content provider,
     * similar to the relationship
     * between a domain name and its website.
     * A convenient string to use for content authority is
     * the package name for the app, since it is guaranteed to be unique on the device.
     */
    public static final String CONTENT_AUTHORITY = "com.androidessence.moviedatabase";

    /**
     * The content authority is used to create the base of all URIs which apps will use to
     * contact this content provider.
     */
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * A list of possible paths that will be appended to the base URI
     * for each of the different
     * tables.
     */
    public static final String PATH_MOVIE = "movie";
    public static final String PATH_GENRE = "genre";

    /**
    * For each of the tables, you should create a class that extends from BaseColumns,
    * which includes an _ID string that is used for the auto increment id of each table.
    * In addition, you'll define a URI for that table, the MIME types of return queries
    * (which are either a directory of multiple rows, or a single item),
    * and a method to build a URI for an individual row in that table.
    * Below are examples for our MovieTable and GenreTable:*/

    /**
     * Create one class for each table that handles all information
     * regarding the table schema and
     * the URIs related to it.
     */
    public static final class MovieEntry implements BaseColumns {

        // Content URI represents the base location for the table
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();

        // These are special type prefixes that specify
        // if a URI returns a list or a specific item
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI  + "/" + PATH_MOVIE;

        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_MOVIE;

        // Define the table schema
        public static final String TABLE_NAME = "movieTable";
        public static final String COLUMN_NAME = "movieName";
        public static final String COLUMN_RELEASE_DATE = "movieReleaseDate";
        public static final String COLUMN_GENRE = "movieGenre";

        // Define a function to build a URI to find a specific movie by it's identifier
        public static Uri buildMovieUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class GenreEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRE).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_GENRE;

        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_GENRE;

        public static final String TABLE_NAME = "genreTable";

        public static final String COLUMN_NAME = "genreName";

        public static Uri buildGenreUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
