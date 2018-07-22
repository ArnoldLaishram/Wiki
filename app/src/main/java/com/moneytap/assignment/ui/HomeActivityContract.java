package com.moneytap.assignment.ui;


import com.moneytap.assignment.model.Page;

import java.util.List;

public interface HomeActivityContract {

    interface View {
        void showProgress(boolean showProgress);

        void onSearchQuerySuccess(List<Page> page);

        void onSearchQueryFailed();
    }

    interface Presenter {
        void searchQuery(String query);
    }
}
