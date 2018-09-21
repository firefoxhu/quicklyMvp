package com.quickly.module.base;

import java.util.List;

/**
 * Created by Meiji on 2017/7/5.
 */

public interface IBaseListView<T> extends IBaseView<T> {


    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     */
    void setPresenter(T presenter);

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

}
