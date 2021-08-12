package com.example.roombuilder;

import android.os.Bundle;

import com.example.roombuilder.database.TranslationRepository;
import com.example.roombuilder.database.models.Translation;
import com.example.roombuilder.database.models.TranslationAn;
import com.example.roombuilder.database.models.Version;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TranslationRepository mRepository;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRepository = new TranslationRepository(this);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
//            mRepository.insert(new Version("en", "1." + i++));
//            Translation translation = new Translation("key" + System.currentTimeMillis(), "value" + System.currentTimeMillis());
//            mRepository.insert(translation);
//
//            List<Translation> trs = mRepository.getAllTranslations();
//            for (Translation tr :
//                    trs) {
//                Log.d("TESTER", "key:" + tr.getKey() + " value:" + tr.getValue());
//            }

//            Translation tr = mRepository.getTranslation("hello");
//            Log.d("TESTER", "key:" + tr.getKey() + " value:" + tr.getValue());

//            String s = getStringResourceByName("test");
//
//            Version tr = mRepository.getVersion("en");
//            Log.d("TESTER", "lang:" + tr.getLanguage() + " ver:" + tr.getVersion());

            readJSONS();
            getAllEquals();

//            writeFile();
        });
    }

    private void getAllEquals() {
            List<Translation> trs = mRepository.getAllTranslations();
            List<TranslationAn> trsAn = mRepository.getAllTranslationsAn();
            String equals = "";
            String sameKey = "";
            String sameValue = "";
            String similarValue = "";
            String uniqueValue = "";

        int equalsCount = 0;
        int sameKeyCount = 0;
        int sameValueCount = 0;
        int similarValueCount = 0;
        int uniqueValueCount = 0;

//            for (Translation tr :
//                    trs) {
//                TranslationAn trAn = mRepository.getTranslationAn(tr.getKey());
//                if (trAn != null) {
//                    if (tr.getValue().equals(trAn.getValue())) {
//                        equals = equals + "key:" + tr.getKey() + " value:" + tr.getValue() + "\n";
//                    } else {
//                        sameKey = sameKey + "key:" + tr.getKey() + " value:" + tr.getValue() + "\n";
//                    }
//                }
//
//                TranslationAn trAnValue = mRepository.getTranslationAnByValue(tr.getValue());
//                if (trAnValue != null) {
//                    if (!tr.getKey().equals(trAnValue.getKey())) {
//                        sameValue = sameValue + "key:" + tr.getKey() + " value:" + tr.getValue() + "\n";
//                    }
//                }
//            }

        for (TranslationAn trAn : trsAn) {
            Translation trByKey = mRepository.getTranslation(trAn.getKey());
            Translation trByValue = mRepository.getTranslationByValue(trAn.getValue());

            Translation similar = mRepository.getSimilar(trAn.getValue());
            if (trByKey == null && trByValue == null) {
                if (similar != null) {
                    similarValue = similarValue + "ankey:\"" + trAn.getKey() + "\"\n" + "ioskey:\"" + similar.getKey() + "\"\nios:\"" + similar.getValue() + "\"\nandroid:\"" + trAn.getValue() + "\"\n\n";
                    similarValueCount++;
                } else {
                    uniqueValue = uniqueValue + "\"" + trAn.getKey() + "\"\n\"" + trAn.getValue() + "\",\n\n";
                    uniqueValueCount++;
                }
            } else {
                if (trByKey != null && trByValue != null) {
                    equals = equals + getTranslationString(trAn);
                    equalsCount++;
                } else if (trByKey != null) {
                    sameKey = sameKey + "key:\"" + trAn.getKey() + "\"\nios:\"" + trByKey.getValue() + "\nandroid:\"" + trAn.getValue() + "\"\n\n";
                    sameKeyCount++;
                } else if (trByValue != null) {
                    sameValue = sameValue + "anKey:\"" + trAn.getKey() + "\" iosKey:\"" + trByValue.getKey() + "\":\"" + trAn.getValue() + "\",\n";
                    sameValueCount++;
                }
            }
        }

        String test = "";
    }

    private String getTranslationString(TranslationAn trAn) {
        return "\"" + trAn.getKey() + "\":\"" + trAn.getValue() + "\",\n";
    }

    private void writeFile() {
        String filename = "translation.txt";
        File dir = getFilesDir();
        File file = new File(dir, filename);

        Log.d("TESTER", "exists? "+file.exists());

        try {
            Log.d("TESTER", "The file path = "+file.getAbsolutePath());
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStringResourceByName(String aString) {
        int resId = getResources().getIdentifier(aString, "string", getPackageName());
        return getString(resId);
    }

    private void readJSONS() {
        try {
            InputStream is = getAssets().open("strings_ios.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String ios = new String(buffer, "UTF-8");

            InputStream is1 = getAssets().open("strings_android_updated.json");
            int size1 = is1.available();
            byte[] buffer1 = new byte[size1];
            is1.read(buffer1);
            is1.close();
            String android = new String(buffer1, "UTF-8");

            try {
                JSONObject json = new JSONObject(ios);
                Iterator<String> iter = json.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    String value = json.getString(key);
                    mRepository.insert(new Translation(key, value));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject json = new JSONObject(android);
                Iterator<String> iter = json.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    String value = json.getString(key);
                    mRepository.insert(new TranslationAn(key, value));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}