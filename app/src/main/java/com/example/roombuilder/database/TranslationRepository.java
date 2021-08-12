package com.example.roombuilder.database;

import android.content.Context;

import com.example.roombuilder.database.dao.TranslationAnDao;
import com.example.roombuilder.database.dao.TranslationDao;
import com.example.roombuilder.database.dao.VersionDao;
import com.example.roombuilder.database.models.Translation;
import com.example.roombuilder.database.models.TranslationAn;
import com.example.roombuilder.database.models.Version;

import java.util.List;

public class TranslationRepository {

    private TranslationDao translationDao;
    private TranslationAnDao translationAnDao;
    private VersionDao versionDao;

    public TranslationRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        translationDao = db.translationDao();
        versionDao = db.versionDao();
        translationAnDao = db.translationAnDao();
    }

    public List<Translation> getAllTranslations() {
        return translationDao.getAll();
    }

    public List<TranslationAn> getAllTranslationsAn() {
        return translationAnDao.getAll();
    }

    public List<Version> getAllVersions() {
        return versionDao.getAll();
    }

    public Translation getTranslation(String key) {
        return translationDao.findByKey(key);
    }

    public TranslationAn getTranslationAn(String key) {
        return translationAnDao.findByKey(key);
    }

    public TranslationAn getTranslationAnByValue(String value) {
        return translationAnDao.findByValue(value);
    }

    public Translation getTranslationByValue(String value) {
        return translationDao.findByValue(value);
    }

    public Translation getSimilar(String value) {
        return translationDao.similarValue(value);
    }

    public Version getVersion(String lang) {
        return versionDao.findByLanguage(lang);
    }

    public void insert(Translation tr) {
        AppDatabase.databaseWriteExecutor.execute(() -> translationDao.insert(tr));
    }

    public void insert(TranslationAn tr) {
        AppDatabase.databaseWriteExecutor.execute(() -> translationAnDao.insert(tr));
    }

    public void insert(Version ver) {
        AppDatabase.databaseWriteExecutor.execute(() -> versionDao.insert(ver));
    }

    public void clear() {
        translationDao.deleteAll();
    }

}
