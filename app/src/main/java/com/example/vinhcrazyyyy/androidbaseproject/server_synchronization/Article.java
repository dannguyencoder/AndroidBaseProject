package com.example.vinhcrazyyyy.androidbaseproject.server_synchronization;

/*Prelude
* Before being able to jump into building our SyncAdapter, we must create
* a few components before we can start*/

/**
 * 1. Creating Model Classes
 * We need to create a simple class to model what our hypothetical article's data
 * should look like.
 * Represents a very simple RSS article*/
public class Article {
    private String id;
    private String title;
    private String content;
    private String link;

    public Article() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
