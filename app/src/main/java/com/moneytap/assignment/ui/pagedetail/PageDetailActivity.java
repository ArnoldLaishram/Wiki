package com.moneytap.assignment.ui.pagedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moneytap.assignment.R;
import com.moneytap.assignment.model.Page;
import com.moneytap.assignment.model.Parse;
import com.moneytap.assignment.Preference;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class PageDetailActivity extends AppCompatActivity implements PageDetailContract.View {

    private static final String PAGE = "PAGE";
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.txt_page_title)
    TextView txtPageTitle;
    @BindView(R.id.txt_page_desc)
    TextView txtPageDesc;
    @BindView(R.id.page_img)
    ImageView imgPage;
    @BindView(R.id.btn_back)
    View btnBack;
    @BindView(R.id.search_progress_bar)
    ProgressBar progressBar;

    @Inject
    PageDetailContract.Presenter weatherPresenter;
    @Inject
    Preference preference;

    public static Intent newIntent(Context context, Page page) {
        Intent newIntent = new Intent(context, PageDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PAGE, Parcels.wrap(page));
        newIntent.putExtras(bundle);
        return newIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        Page page = Parcels.unwrap(extras.getParcelable(PAGE));

        if (page.getThumbnail() != null && page.getThumbnail().getSource() != null) {
            imgPage.setVisibility(View.VISIBLE);
            Picasso.get().load(page.getThumbnail().getSource()).fit().into(imgPage);
        } else {
            imgPage.setVisibility(View.GONE);
        }
        if (page.getDesc() != null) {
            txtPageDesc.setVisibility(View.VISIBLE);
            txtPageDesc.setText(page.getDesc());
        } else {
            txtPageDesc.setVisibility(View.GONE);
        }

        txtPageTitle.setText(page.getTitle());
        weatherPresenter.getPage(page.getTitle());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void showProgress(boolean showProgress) {
        if (showProgress) startLoader();
        else stopLoader();
    }

    @Override
    public void onGetPageDeatilSuccess(Parse parse) {
        String encodedHtml = Base64.encodeToString(parse.getText().getBytes(),
                Base64.NO_PADDING);
        webView.loadData(encodedHtml, "text/html", "base64");
    }

    @Override
    public void onGetPageDetailFailed() {

    }

    private void startLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void stopLoader() {
        progressBar.setVisibility(View.GONE);
    }

}
