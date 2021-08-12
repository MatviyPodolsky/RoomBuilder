package com.example.roombuilder.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roombuilder.database.dao.TranslationAnDao;
import com.example.roombuilder.database.dao.TranslationDao;
import com.example.roombuilder.database.dao.VersionDao;
import com.example.roombuilder.database.models.Translation;
import com.example.roombuilder.database.models.TranslationAn;
import com.example.roombuilder.database.models.Version;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        Translation.class,
        TranslationAn.class,
        Version.class
}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TranslationDao translationDao();
    public abstract TranslationAnDao translationAnDao();
    public abstract VersionDao versionDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "translation_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
