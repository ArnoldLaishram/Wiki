package com.moneytap.assignment.di.component;

import android.app.Application;

import com.moneytap.assignment.WikiApplication;
import com.moneytap.assignment.di.module.ActivityBuilderModule;
import com.moneytap.assignment.di.module.ApplicationModule;
import com.moneytap.assignment.di.module.NetworkModule;
import com.moneytap.assignment.di.module.UtilModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * A @Component annotated interface defines the connection between the provider of objects (modules) and the objects which express a dependency.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        NetworkModule.class,
        ActivityBuilderModule.class,
        UtilModule.class,
})
public interface ApplicationComponent {

    void inject(WikiApplication app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

}
