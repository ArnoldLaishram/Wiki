package com.moneytap.assignment.ui;

import com.moneytap.assignment.WikiService;
import com.moneytap.assignment.model.SearchQueryResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivityPresenter implements HomeActivityContract.Presenter {

    private final HomeActivityContract.View viewInterator;
    private WikiService wikiService;
    private Call<SearchQueryResponse> searchQueryCall;

    @Inject
    public HomeActivityPresenter(HomeActivityContract.View viewInterator, WikiService wikiService) {
        this.viewInterator = viewInterator;
        this.wikiService = wikiService;
    }

    public void cancelNetworkCall() {
        if (searchQueryCall != null) {
            searchQueryCall.cancel();
            viewInterator.showProgress(false);
        }
    }

    @Override
    public void searchQuery(String query) {
        this.viewInterator.showProgress(true);
        searchQueryCall = this.wikiService.searchQuery(query, query);
        searchQueryCall.enqueue(new Callback<SearchQueryResponse>() {
            @Override
            public void onResponse(Call<SearchQueryResponse> call, Response<SearchQueryResponse> response) {
                viewInterator.showProgress(false);
                if (response.code() >= 200 && response.code() <= 300) {
                    if (response.body() != null && response.body().getQuery() != null)
                        viewInterator.onSearchQuerySuccess(response.body().getQuery().getPages());
                    return;
                }
                viewInterator.onSearchQueryFailed();
            }

            @Override
            public void onFailure(Call<SearchQueryResponse> call, Throwable t) {
                if (searchQueryCall.isCanceled()) return;
                viewInterator.showProgress(false);
                viewInterator.onSearchQueryFailed();
            }
        });
    }
}
