package com.moneytap.assignment.ui.pagedetail;

import com.moneytap.assignment.WikiService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageDetailPresenter implements PageDetailContract.Presenter {

    private final PageDetailContract.View viewInterator;
    private WikiService wikiService;

    @Inject
    public PageDetailPresenter(PageDetailContract.View viewInterator, WikiService wikiService) {
        this.viewInterator = viewInterator;
        this.wikiService = wikiService;
    }

    public void cancelNetworkCall() {

    }

    @Override
    public void getPage(String pageId) {
        viewInterator.showProgress(true);


    }
}
