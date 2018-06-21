package com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM;

/*
    Question: How do I store dates into Room?

    Answer: Room supports type converters:*/

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @ColumnInfo
    Date timestamp;

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
