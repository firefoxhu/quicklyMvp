package com.quickly.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected   View mRootView;

    /**
     * 获取页面布局文件
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    public abstract void init(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutResId(), container, false);
        init(savedInstanceState);
        return mRootView;
    }

}
