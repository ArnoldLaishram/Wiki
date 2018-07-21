package com.moneytap.assignment.di.module;

import com.moneytap.assignment.WikiService;
import com.moneytap.assignment.ui.pagedetail.PageDetailActivity;
import com.moneytap.assignment.ui.pagedetail.PageDetailContract;
import com.moneytap.assignment.ui.pagedetail.PageDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PageDetailActivityModule {

    @Provides
    PageDetailContract.View provideWeatherViewInteractor(PageDetailActivity pageDetailActivity) {
        return pageDetailActivity;
    }

    @Provides
    PageDetailContract.Presenter provideWeatherPresenter(PageDetailPresenter pageDetailPresenter) {
        return pageDetailPresenter;
    }
}