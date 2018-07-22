package com.moneytap.assignment.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.moneytap.assignment.R;
import com.moneytap.assignment.model.Page;
import com.moneytap.assignment.ui.pagedetail.PageDetailActivity;
import com.moneytap.assignment.util.PreferenceUtil;
import com.moneytap.assignment.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity implements HomeActivityContract.View, SearchResultAdapter.OnSearchItemClickListener, SearchHistoryAdapter.OnRecentItemClickListener {


    @BindView(R.id.mainLayout)
    FrameLayout mainLayout;
    @BindView(R.id.homeCard)
    CardView homeCard;
    @BindView(R.id.homeCardContainer)
    FrameLayout homeCardContainer;

    private CardView searchCard;
    private FrameLayout searchLayout;
    private View alphaView;
    private RecyclerView searchHistoryRv;
    private RecyclerView searchResultRv;
    private View searchHistoryView;
    private EditText edtSearch;
    private ProgressBar progressBar;

    private boolean isInSearchView = false;

    private SearchResultAdapter searchResultAdapter;
    private SearchHistoryAdapter searchHistoryAdapter;

    @Inject
    HomeActivityPresenter homeActivityPresenter;
    @Inject
    PreferenceUtil preferenceUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();

        isInSearchView = false;

        homeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateToSearchView();
            }
        });

        alphaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateToHomeView();
            }
        });
    }

    private TextWatcher searchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!isInSearchView) return;
            hideSearchHistoryView();
            homeActivityPresenter.cancelNetworkCall();
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isInSearchView) return;
            homeActivityPresenter.searchQuery(s.toString());
        }
    };

    private void animateToHomeView() {
        isInSearchView = false;
        // hideKeyBoard before saving details
//        Util.hideKeyboard(this, edtSearch);
        edtSearch.clearFocus();
        saveRecentSearches();
        TransitionSet transitionSet = new TransitionSet()
                .addTransition(new Fade()
                        .addTarget(alphaView))
                .addTransition(new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeTransform())
                        .addTarget(R.id.homeCard)
                        .addTarget(searchCard));

        TransitionManager.beginDelayedTransition(mainLayout, transitionSet);

        homeCardContainer.addView(homeCard);
        mainLayout.removeView(searchLayout);
    }

    private void saveRecentSearches() {
        String searchString = edtSearch.getText().toString();
        if (searchString.isEmpty()) return;
        HashSet<String> set = (HashSet<String>) preferenceUtil.read(PreferenceUtil.SEARCH_HISTORY, HashSet.class);
        if (set == null) set = new HashSet<>();
        set.add(searchString);
        preferenceUtil.save(PreferenceUtil.SEARCH_HISTORY, set);
    }

    private void animateToSearchView() {
        showRecentSearches();
        isInSearchView = true;
        TransitionSet transitionSet = new TransitionSet()
                .addTransition(new Fade()
                        .addTarget(R.id.alphaView))
                .addTransition(new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeTransform())
                        .addTarget(homeCard)
                        .addTarget(R.id.searchCard));

        TransitionManager.beginDelayedTransition(mainLayout, transitionSet);

        mainLayout.addView(searchLayout);
        homeCardContainer.removeView(homeCard);
    }

    private void showRecentSearches() {

        edtSearch.setText("");
        searchResultAdapter.setSearchResults(new ArrayList<Page>());

        HashSet<String> set = (HashSet<String>) preferenceUtil.read(PreferenceUtil.SEARCH_HISTORY, HashSet.class);
        if (set == null) {
            hideSearchHistoryView();
            return;
        }

        showSearchHistoryView();
        searchHistoryAdapter.setSearchResults(new ArrayList<String>(set));
    }

    private void initViews() {
        // Setup the toolBar
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // init search layout views
        searchLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.search_layout, null);
        alphaView = searchLayout.findViewById(R.id.alphaView);
        searchCard = searchLayout.findViewById(R.id.searchCard);
        edtSearch = searchLayout.findViewById(R.id.edt_search);
        searchHistoryRv = searchLayout.findViewById(R.id.recycler_search_history);
        searchResultRv = searchLayout.findViewById(R.id.recycler_search_result);
        progressBar = searchLayout.findViewById(R.id.search_progress_bar);
        searchHistoryView = searchLayout.findViewById(R.id.recycler_search_history_layout);
        edtSearch.addTextChangedListener(searchTextWatcher);

        // init recycler view
        searchHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        searchResultRv.setLayoutManager(new LinearLayoutManager(this));

        List<Page> searchResults = new ArrayList<Page>();
        searchResultAdapter = new SearchResultAdapter(searchResults, this);
        searchResultRv.setAdapter(searchResultAdapter);

        searchHistoryAdapter = new SearchHistoryAdapter(new ArrayList<String>(), this);
        searchHistoryRv.setAdapter(searchHistoryAdapter);
    }

    private void hideSearchHistoryView() {
        searchHistoryView.setVisibility(View.GONE);
    }

    private void showSearchHistoryView() {
        searchHistoryView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (isInSearchView) {
            animateToHomeView();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void showProgress(boolean showProgress) {
        if (showProgress) {
            progressBar.setVisibility(View.VISIBLE);
            return;
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSearchQuerySuccess(List<Page> pages) {
        searchResultAdapter.setSearchResults(pages);
    }

    @Override
    public void onSearchQueryFailed() {

    }

    @Override
    public void onSearchItemClicked(Page page) {
        startActivity(PageDetailActivity.newIntent(HomeActivity.this, page));
    }

    @Override
    public void onRecentItemClicked(String recent) {
        edtSearch.setText(recent);
        homeActivityPresenter.searchQuery(recent);
        hideSearchHistoryView();
    }
}
