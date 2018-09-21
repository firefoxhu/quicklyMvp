package com.quickly.module.news;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.quickly.R;
import com.quickly.Register;
import com.quickly.bean.BannerBean;
import com.quickly.bean.CategoryBean;
import com.quickly.bean.LoadingBean;
import com.quickly.bean.LoadingEndBean;
import com.quickly.binder.LoadingViewBinder;
import com.quickly.module.base.BaseListFragment;
import com.quickly.utils.DiffCallback;
import com.quickly.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class NewsArticleView extends BaseListFragment<INewsArticle.Presenter> implements INewsArticle.View  {

    private static final String TAG = "NewsArticleView";
    private String categoryId;

    public static NewsArticleView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        NewsArticleView view = new NewsArticleView();
        view.setArguments(bundle);
        return view;
    }

    @Override
    protected void initData() throws NullPointerException {
        categoryId = getArguments().getString(TAG);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerNewsArticleItem(adapter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void fetchData() {
        super.fetchData();
        observable.subscribe(integer -> adapter.notifyDataSetChanged());
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items();
        newItems.add(new BannerBean());
        newItems.add(new CategoryBean.DataBean.ListBean());
        newItems.addAll(list);
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void setPresenter(INewsArticle.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsArticlePresenter(this);
        }
    }



}
