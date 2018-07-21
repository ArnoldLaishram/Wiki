package com.moneytap.assignment.ui.pagedetail;

public interface PageDetailContract {

    interface View {
        void showProgress(boolean showProgress);
        void onGetPageDeatilSuccess();
        void onGetPageDetailFailed();
    }

    interface Presenter {
        void getPage(String pageId);
    }
}
