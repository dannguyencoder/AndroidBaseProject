package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

import org.json.JSONObject;

/**
 * 2. Create a Parsing Utility
 * We need to create a utility that will conver JSON data into our Article model*/

/*This is an example parser to 'parse' pretend json news feed*/
public class ArticleParser {
    public static Article parse(JSONObject jsonArticle) {
        Article article = new Article();
        article.setId(jsonArticle.optString("id"));
        article.setTitle(jsonArticle.optString("title"));
        article.setContent(jsonArticle.optString("content"));
        article.setLink(jsonArticle.optString("link"));
        return article;
    }
}
