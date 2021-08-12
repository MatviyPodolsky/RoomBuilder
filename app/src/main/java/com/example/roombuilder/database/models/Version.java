package com.example.roombuilder.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Version {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "language")
    public String language;

    @ColumnInfo(name = "version")
    public String version;

    public Version(@NonNull String language, @NonNull String version) {
        this.language = language;
        this.version = version;
    }

    @NonNull
    public String getLanguage() {
        return language;
    }

    public void setLanguage(@NonNull String language) {
        this.language = language;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
