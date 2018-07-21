package com.moneytap.assignment.ui.pagedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.moneytap.assignment.util.PreferenceUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class PageDetailActivity extends AppCompatActivity implements PageDetailContract.View {

    @Inject
    PageDetailContract.Presenter weatherPresenter;
    @Inject
    PreferenceUtil preferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_weather);

        ButterKnife.bind(this);
    }

    @Override
    public void showProgress(boolean showProgress) {
        if(showProgress) startLoader();
        else stopLoader();
    }

    @Override
    public void onGetPageDeatilSuccess() {

    }

    @Override
    public void onGetPageDetailFailed() {

    }

    private void startLoader() {

    }

    private void stopLoader() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
