package com.quickly.module.content;

import com.quickly.bean.ArticleItem;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.module.base.IBaseListView;
import com.quickly.module.base.IBasePresenter;
import com.quickly.module.base.IBaseView;

public interface INewsContent {

    interface View extends IBaseView<Presenter> {

        /**
         * 加载网页
         */
        void  onSetWebView(String url,boolean flag);
    }

    interface Presenter extends IBasePresenter{
        /**
         * 请求数据
         */
        void doLoadData(NewsArticleItem.DataBean.ListBean dataBean);

    }


}
