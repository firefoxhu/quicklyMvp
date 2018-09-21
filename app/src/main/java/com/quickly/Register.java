package com.quickly;

import android.support.annotation.NonNull;

import com.quickly.bean.BannerBean;
import com.quickly.bean.CategoryBean;
import com.quickly.bean.LoadingBean;
import com.quickly.bean.LoadingEndBean;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.binder.BannerViewBinder;
import com.quickly.binder.CategoryViewBinder;
import com.quickly.binder.LoadingEndViewBinder;
import com.quickly.binder.LoadingViewBinder;
import com.quickly.binder.news.NewsArticleImgViewBinder;

import me.drakeet.multitype.MultiTypeAdapter;
public class Register {


    public static void registerNewsArticleItem(@NonNull MultiTypeAdapter adapter){

        // 这个是一个数据源对多个不同视图
        adapter.register(NewsArticleItem.DataBean.ListBean.class).to(
                new NewsArticleImgViewBinder()
        ).withClassLinker((position, data) ->NewsArticleImgViewBinder.class);

        // 多个 数据源对多个视图
        adapter.register(BannerBean.class, new BannerViewBinder());
        adapter.register(CategoryBean.DataBean.ListBean.class, new CategoryViewBinder());


    }




}
