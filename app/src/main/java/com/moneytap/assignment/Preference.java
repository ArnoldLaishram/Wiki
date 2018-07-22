package com.moneytap.assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import javax.inject.Inject;

public class Preference {

    public static final String SEARCH_HISTORY = "SEARCH_HISTORY";
    private Gson gson;
    private SharedPreferences preferences;

    @Inject
    public Preference(Context context, Gson gson) {
        this.gson = gson;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // Save methods
    public void save(String key, Object object) {
        save(key, gson.toJson(object));
    }

    private void save(String key, String value) {
        getEditor().putString(key, value).apply();
    }

    // Read methods
    public Object read(String key, Class classType) {
        return gson.fromJson(readString(key), classType);
    }

    private String readString(String key) {
        try {
            return preferences.getString(key, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

} 