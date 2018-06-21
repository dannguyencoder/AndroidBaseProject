package com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

public class UserWithOrganization {
    // @Embedded notation flattens the properties of the User object into the object,
    // preserving encapsulation.
    @Embedded
    User user;

    // organization_name renamed during SELECT query w/ Organization.name
    // AS organizaiton_name
    @ColumnInfo(name = "organization_name")
    String organizationName;

    /*You can then access the properties of User within this object
    as we would as a normal Java object:

    UserWithOrganization userWithOrg = new UserWithOrganization();
    User user = userWithOrg.user;*/

    /*Suppose you wanted to query all the columns in the Organization table
    and have this information stored in this object. The problem of course is that
    the User and Organization both have an id column and conflict.
    To get around this issue, the prefix parameter can be used
    with the @Embedded notation:*/
    // @Embedded notation flattens the properties of the User object
    // into the object, preserving encapsulation.
    @Embedded User user;

    // All fields returned from the Organization table must be prefixed with org_
    // (i.e. org_id, org_name, etc.)
    @Embedded(prefix="org_")
    Organization organization;
}
