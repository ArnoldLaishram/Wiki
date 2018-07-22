package com.moneytap.assignment.ui.pagedetail;

import com.moneytap.assignment.model.Parse;

public interface PageDetailContract {

    interface View {
        void showProgress(boolean showProgress);

        void onGetPageDeatilSuccess(Parse parse);

        void onGetPageDetailFailed();
    }

    interface Presenter {
        void getPage(String pageName);
    }
}
