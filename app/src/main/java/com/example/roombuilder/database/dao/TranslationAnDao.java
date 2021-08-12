package com.example.roombuilder.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roombuilder.database.models.Translation;
import com.example.roombuilder.database.models.TranslationAn;

import java.util.List;

@Dao
public interface TranslationAnDao {

    @Query("SELECT * FROM translationan")
    List<TranslationAn> getAll();

    @Query("SELECT * FROM translationan WHERE translation_key = :key LIMIT 1")
    TranslationAn findByKey(String key);

    @Query("SELECT * FROM translationan WHERE translation_value = :value LIMIT 1")
    TranslationAn findByValue(String value);

    @Query("SELECT * FROM translationan WHERE translation_value LIKE :value OR :value LIKE translation_value LIMIT 1")
    Translation similarValue(String value);

    @Insert
    void insertAll(TranslationAn... translations);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TranslationAn translation);

    @Delete
    void delete(TranslationAn translation);

    @Query("DELETE FROM translationan")
    void deleteAll();

}
