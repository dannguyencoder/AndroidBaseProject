package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

import android.net.Uri;

/**
 * 3. Create Contract Classes
 * Just like recommend in other Wiki pages on here, it's a good idea to define your database
 * and provider structure concretely. (Refer to this great article for a more
 * detailed explanation)
 * */
/*Define all local entities in the class.*/
public class ArticleContract {
    //ContentProvider information
    public static final String CONTENT_AUTHORITY = "com.exemple_sync";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    static final String PATH_ARTICLES = "articles";

    //Database information
    static final String DB_NAME = "articles_db";
    static final int DB_VERSION = 1;

    /*
    * This represents our SQLite table for our articles
    * */
    public static abstract class Articles {
        public static final String NAME = "articles";
        public static final String COL_ID = "articleId";
        public static final String COL_TITLE = "articleTitle";
        public static final String COL_CONTENT = "articleContent";
        public static final String COL_LINK = "articleLink";

        //ContentProvider information for articles
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTICLES).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_ARTICLES;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_ARTICLES;
    }
}
