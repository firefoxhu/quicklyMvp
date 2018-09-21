package com.quickly.module.news;
import com.quickly.api.INewsApi;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.utils.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsArticlePresenter implements INewsArticle.Presenter{

    private static final String TAG = "NewsArticlePresenter";

    private INewsArticle.View view;

    private List<NewsArticleItem.DataBean.ListBean> dataList = new ArrayList<>();

    private boolean canLoadMore = true;

    private int page = 0;

    private Random mRandom = new Random();

    public NewsArticlePresenter(INewsArticle.View view) {
        this.view = view;
    }


    @Override
    public void doLoadData(String... category) {

        if(!canLoadMore)return;

        if(dataList.size() > 150){
            dataList.clear();
        }

        RetrofitFactory.getRetrofit().create(INewsApi.class).getRecommend(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose())
                .subscribe(data->{

                    if(data.getCode() == 0) {

                        if(data.getData().isHasNext()) {
                            this.canLoadMore = data.getData().isHasNext();
                        }

                        if(data.getData().getList().size() > 0) {
                            doSetAdapter(data.getData().getList());
                        }else {
                            doShowNoMore();
                        }

                    }
                },throwable ->doShowNetError());


    }

    @Override
    public void doLoadMoreData() {
        page = mRandom.nextInt(100);
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<NewsArticleItem.DataBean.ListBean> dataBeen) {
        dataList.addAll(dataBeen);
        view.onSetAdapter(dataList);
        view.onHideLoading();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
    }

    @Override
    public void doRefresh() {
        if(dataList.size() != 0) {
            dataList.clear();
        }
        page = mRandom.nextInt(100);
        view.onShowLoading();
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public boolean noDataLoad() {
        return canLoadMore;
    }


}
