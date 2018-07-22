package com.moneytap.assignment.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.moneytap.assignment.util.PreferenceUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {

    @Provides
    PreferenceUtil providePreference(Context context, Gson gson) {
        return new PreferenceUtil(context, gson);
    }
}