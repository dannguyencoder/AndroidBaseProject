package com.example.vinhcrazyyyy.androidbaseproject.persistence.sqlite;

//In order to to access our records from the database more easily, we should create a model class
//for each of our resources. In this case let's define Post, and a User model:
public class Post {
    public User user;
    public String text;
}
