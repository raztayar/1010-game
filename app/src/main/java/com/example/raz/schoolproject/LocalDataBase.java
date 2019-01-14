package com.example.raz.schoolproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class LocalDataBase {

    private SharedPreferences preferences;

    public LocalDataBase(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public <T> void save(String key, T value) {
        String stringValue = new Gson().toJson(value);
        preferences.edit().putString(key, stringValue).apply();
    }

    public <T> T load(String key, Class<T> clazz) {
        return new Gson().fromJson(preferences.getString(key, null), clazz);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }
}
