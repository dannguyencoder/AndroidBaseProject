package com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.models.User;

/*Defining your data access Objects
We can declare the types of SQL queries we want to
perform in our data access object (DAO) layer.
The following annotations can be used
to get, insert, and delete a User object:*/
@Dao
public interface UserDao {
    @Query("SELECT * FROM User where userId := :id")
    public User getById(int id);

    /*Querying Rows
    All queries must be defined in the Data Access Object (DAO):
    If we want to perform a User query that also retrieves the organization info
    into memory, we can do the following: */
    // declare inner join here
    @Query("SELECT User.*, Organization.name AS organization_name FROM User " +
            "INNER JOIN Organization " +
            "ON User.organization_id = Organization.id WHERE User.id = :id")
    public UserWithOrganization getWithOrgById(int id);
    /*Notice how the organization name has been renamed using the SQL AS query.
    This helps to sidestep naming conflicts since both the User and Organization
    both share the same field name. We can then need to declare a regular Java object
    that includes both a User and the organization name:*/

    /*Updating Rows
    Updating rows require defining an @Insert annotation.
    The primary key will be returned if the return type is declared as a Long type:*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // return primary key as Long
    public Long insertUser(User user);

    /*Multiple primary keys can also be returned if the DAO includes multiple parameters:*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long[] insertUsers(User... users);

    /*Deleting Rows
    Deleting requires either a class annotated as an Entity or a collection:*/
    @Delete
    public void deleteUser(User user);

    @Delete
    public void deleteAll(User user1, User user2);
}
