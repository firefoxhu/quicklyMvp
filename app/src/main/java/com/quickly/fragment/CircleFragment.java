package com.quickly.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.quickly.R;
import com.quickly.fragment.circle.Item;
import com.quickly.common.SmartPagerAdapter;

import cn.jzvd.JZVideoPlayerStandard;

public class CircleFragment extends Fragment{


    /**
     * UI
     */

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    /**
     * 适配器
     */
    // private SmartPagerAdapter mSmartPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_circle_layout,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        // UI初始化
        mTabLayout = root.findViewById(R.id.tableLayout);
        mViewPager = root.findViewById(R.id.viewPager);

        // 适配器设置
        mViewPager.setAdapter(new SmartPagerAdapter(getChildFragmentManager(),Item.values()));

        mViewPager.setOffscreenPageLimit(3);


        // 关联tab和viewpager
        mTabLayout.setupWithViewPager(mViewPager,true);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // 优化
        if(hidden) {
            JZVideoPlayerStandard.releaseAllVideos();
         }
    }
}
