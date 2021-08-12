package com.example.roombuilder.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roombuilder.database.models.Translation;

import java.util.List;

@Dao
public interface TranslationDao {

    @Query("SELECT * FROM translation")
    List<Translation> getAll();

    @Query("SELECT * FROM translation WHERE translation_key = :key LIMIT 1")
    Translation findByKey(String key);

    @Query("SELECT * FROM translation WHERE translation_value = :value LIMIT 1")
    Translation findByValue(String value);

    @Query("SELECT * FROM translation WHERE translation_value LIKE :value OR :value LIKE translation_value LIMIT 1")
    Translation similarValue(String value);

    @Insert
    void insertAll(Translation... translations);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Translation translation);

    @Delete
    void delete(Translation translation);

    @Query("DELETE FROM translation")
    void deleteAll();

}
