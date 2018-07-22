package com.moneytap.assignment.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide ApplicationModule & Context dependency.
 * <p>
 * Classes annotated with @Module are responsible for providing objects which can be injected.
 * Such classes define methods annotated with @Provides. The returned objects from these methods are available for dependency injection.
 */

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public Context provideContext(Application application) {
        return application;
    }

}
