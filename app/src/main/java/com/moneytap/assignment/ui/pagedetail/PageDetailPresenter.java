package com.moneytap.assignment.ui.pagedetail;

import com.moneytap.assignment.WikiService;
import com.moneytap.assignment.model.PageResponse;
import com.moneytap.assignment.model.SearchQueryResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageDetailPresenter implements PageDetailContract.Presenter {

    private final PageDetailContract.View viewInterator;
    private WikiService wikiService;
    private Call<PageResponse> pageCall;

    @Inject
    public PageDetailPresenter(PageDetailContract.View viewInterator, WikiService wikiService) {
        this.viewInterator = viewInterator;
        this.wikiService = wikiService;
    }

    public void cancelNetworkCall() {

    }

    @Override
    public void getPage(String pageName) {
        viewInterator.showProgress(true);
        pageCall = this.wikiService.getPage(pageName);
        pageCall.enqueue(new Callback<PageResponse>() {
            @Override
            public void onResponse(Call<PageResponse> call, Response<PageResponse> response) {
                viewInterator.showProgress(false);
                if (response.code() >= 200 && response.code() <= 300) {
                    if (response.body() != null)
                        viewInterator.onGetPageDeatilSuccess(response.body().getParse());
                    return;
                }
                viewInterator.onGetPageDetailFailed();
            }

            @Override
            public void onFailure(Call<PageResponse> call, Throwable t) {
                if (pageCall.isCanceled()) return;
                viewInterator.showProgress(false);
                viewInterator.onGetPageDetailFailed();
            }
        });

    }
}
