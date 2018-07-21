package com.moneytap.assignment.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class PreferenceUtil {

    public static final String LAST_LOCATION = "lastLocation";
    public static final String SEARCH_HISTORY = "SEARCH_HISTORY";
    private Gson gson;
    private SharedPreferences preferences;

    public PreferenceUtil(Context context, Gson gson) {
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

    // Remove & Clear methods
    public void remove(String key) {
        getEditor().remove(key).apply();
    }

    public void clear() {
        getEditor().clear().apply();
    }

    // Read methods
    public Object read(String key, Class classType) {
        return gson.fromJson(readString(key, null), classType);
    }

    private String readString(String key, String defaultValue) {
        try {
            return preferences.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

} 