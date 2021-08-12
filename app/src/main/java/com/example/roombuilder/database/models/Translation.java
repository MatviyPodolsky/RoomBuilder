package com.example.roombuilder.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Translation {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "translation_key")
    public String key;

    @ColumnInfo(name = "translation_value")
    public String value;

    public Translation(@NonNull String key, @NonNull String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
