package com.quickly.module.news;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.module.base.IBaseListView;
import com.quickly.module.base.IBasePresenter;

import java.util.List;

public class INewsArticle {

    interface View extends IBaseListView<Presenter> {
    }

    interface Presenter extends IBasePresenter {
        /**
         * 请求数据
         */
        void doLoadData(String... params);

        /**
         * 设置适配器
         */
        void doSetAdapter(List<NewsArticleItem.DataBean.ListBean> dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();


    }
}
