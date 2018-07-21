package com.moneytap.assignment.di.module;

import com.moneytap.assignment.ui.HomeActivity;
import com.moneytap.assignment.ui.pagedetail.PageDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {PageDetailActivityModule.class})
    abstract PageDetailActivity bindWeatherActivity();

    @ContributesAndroidInjector(modules = {HomeActivityModule.class})
    abstract HomeActivity bindHomeActivity();

}
