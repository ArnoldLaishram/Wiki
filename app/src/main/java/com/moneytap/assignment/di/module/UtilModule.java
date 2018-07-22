package com.moneytap.assignment.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.moneytap.assignment.Preference;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {

    @Provides
    Preference providePreference(Context context, Gson gson) {
        return new Preference(context, gson);
    }
}