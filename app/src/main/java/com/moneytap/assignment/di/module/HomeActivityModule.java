package com.moneytap.assignment.di.module;

import com.moneytap.assignment.ui.HomeActivity;
import com.moneytap.assignment.ui.HomeActivityContract;
import com.moneytap.assignment.ui.HomeActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    @Provides
    HomeActivityContract.View provideWeatherViewInteractor(HomeActivity homeActivity) {
        return homeActivity;
    }

    @Provides
    HomeActivityContract.Presenter provideWeatherPresenter(HomeActivityPresenter homeActivityPresenter) {
        return homeActivityPresenter;
    }
}