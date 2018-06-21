package com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/*Defining Foreign Keys
Room allows foreign keys to be defined between objects,
but explicitly disallows traversing these relations automatically.
For instance, trying to lookup an Organization object in User object in memory
cannot be done.
Defining the constraints between these two tables however can still be done:*/
@Entity(foreignKeys = @ForeignKey(entity=Organization.class,
        parentColumns="id", childColumns="organization_id"))
public class User  {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    Long id;

    @ColumnInfo
    String name;

    @ColumnInfo
    Long organization_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }
}
