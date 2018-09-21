package com.quickly.module.mvp;

import com.quickly.module.mvp.base.IMvpBasePresenter;
import com.quickly.module.mvp.base.IMvpBaseView;

import java.util.List;

public interface NewsArticleContract {


    interface Presenter extends IMvpBasePresenter {

        // 初始化加载数据
        void loadData(Object ...args);

        // 加载更多数据
        void loadMoreDate();

    }

    interface View extends IMvpBaseView<Presenter> {

        // 显示加载
        void showLoading();

        // 刷新设置适配器数据
        void refreshUIByData(List<?> data);

        // 显示错误页面
        void showError();
    }


}
