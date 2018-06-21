package com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Organization {
    // ... field definitions that map to columns go here ...

    /*Next, we need to add annotations for each of the fields
    within our model class that will map to columns in the database table:*/
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    Long id;

    @ColumnInfo
    String name;

    /*NOTE: You must define at least one column to be the primary key.
    You can also define composite primary keys by reviewing this section.
    Use the autoGenerate=true annotation to make the column auto-increment.
    (Note that if you add this later, you need to update the schema of the database.)*/

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
}