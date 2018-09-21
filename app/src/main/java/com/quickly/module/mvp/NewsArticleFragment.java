package com.quickly.module.mvp;

import android.view.View;

import com.quickly.module.mvp.base.MvpBaseFragment;

import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

public class NewsArticleFragment extends MvpBaseFragment implements NewsArticleContract.View{

    private NewsArticleContract.Presenter mPresenter;

    private MultiTypeAdapter mAdapter;

    public static NewsArticleFragment newInstance(){
        return new NewsArticleFragment();
    }


    @Override
    protected int attachLayoutId() {
        return 0;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        mPresenter.loadData();
    }




    @Override
    public void showLoading() {

    }

    @Override
    public void refreshUIByData(List<?> data) {
        mAdapter.setItems(data);
    }

    @Override
    public void showError() {

    }

    @Override
    public void setPresenter(NewsArticleContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
