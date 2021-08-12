package com.example.roombuilder.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roombuilder.database.models.Version;

import java.util.List;

@Dao
public interface VersionDao {

    @Query("SELECT * FROM version")
    List<Version> getAll();

    @Query("SELECT * FROM version WHERE language = :lang LIMIT 1")
    Version findByLanguage(String lang);

    @Insert
    void insertAll(Version... versions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Version version);

    @Delete
    void delete(Version version);

    @Query("DELETE FROM version")
    void deleteAll();

}
