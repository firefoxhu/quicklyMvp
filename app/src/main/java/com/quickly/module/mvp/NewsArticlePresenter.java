package com.quickly.module.mvp;

import java.util.Arrays;

public class NewsArticlePresenter implements NewsArticleContract.Presenter {

    private NewsArticleContract.View mView;


    public NewsArticlePresenter(NewsArticleContract.View view){
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void loadData(Object... args) {
        mView.showLoading();
        mView.refreshUIByData(Arrays.asList());
        mView.showError();
    }

    @Override
    public void loadMoreDate() {

    }

    @Override
    public void start() {

    }
}
