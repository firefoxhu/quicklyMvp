package com.quickly;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.quickly.fragment.CircleFragment;
import com.quickly.fragment.HomeFragment;
import com.quickly.module.base.BaseActivity;
import com.quickly.module.news.NewsArticleView;

public class MainActivity extends BaseActivity implements OnBottomNavigationItemClickListener {

    private static final String TAG = "MainActivity";
    private static final String POSITION ="position";
    private static final String SELECT_ITEM = "bottomNavigationSelectItem";
    private static final int MENU_HOME = 0;
    private static final int MENU_CIRCLE = 1;
    private static final int MENU_VIDEO = 2;
    private static final int MENU_MINE = 3;

    private int position = 0;

    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationViewMenu;
    private long exitTime = 0;
    private long firstClickTime = 0;

    // 存放fragment的容器
    private Fragment[] mFragmentMenus = new Fragment[3];

    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if(savedInstanceState != null) {
            mBottomNavigationViewMenu.selectTab(savedInstanceState.getInt(POSITION));
        }else {
           mBottomNavigationViewMenu.selectTab(MENU_HOME);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
    }

    private void initView() {
        mBottomNavigationViewMenu = findViewById(R.id.main_bottom_navigation);

        mBottomNavigationViewMenu.addTab(new BottomNavigationItem("首页", ContextCompat.getColor(this, R.color.colorAccent), R.mipmap.ic_menu_fox));
        mBottomNavigationViewMenu.addTab(new BottomNavigationItem("发现", ContextCompat.getColor(this, R.color.colorPrimary), R.mipmap.ic_menu_find));
        mBottomNavigationViewMenu.addTab(new BottomNavigationItem("视频", ContextCompat.getColor(this, R.color.colorPrimary),  R.mipmap.ic_menu_video));
        mBottomNavigationViewMenu.setOnBottomNavigationItemClickListener(this);

        // 默认创建首页并添加
        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        // 添加初始化页面
        mFragmentMenus[0] = NewsArticleView.newInstance("123213");
        fragmentTransaction.replace(R.id.container,mFragmentMenus[0]);
        fragmentTransaction.commit();


    }



    @Override
    public void onNavigationItemClick(int index) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        position = index;
        switch (index){
            case MENU_HOME:
                if(mFragmentMenus[index] == null) {
                    mFragmentMenus[index] = NewsArticleView.newInstance("123213");
                    fragmentTransaction.add(R.id.container,mFragmentMenus[index]);
                }
            break;

            case MENU_CIRCLE:
                if(mFragmentMenus[index] == null) {
                    mFragmentMenus[index] = new CircleFragment();
                    fragmentTransaction.add(R.id.container,mFragmentMenus[index]);
                }

            break;

            case MENU_VIDEO:
                if(mFragmentMenus[index] == null) {
                    mFragmentMenus[index] = new HomeFragment();
                    fragmentTransaction.add(R.id.container,mFragmentMenus[index]);
                }

            break;
        }

        fragmentTransaction.show(mFragmentMenus[index]);
        fragmentTransaction.commit();
    }


    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if(mFragmentMenus != null) {
            for (int i = 0; i < mFragmentMenus.length; i++) {
                if(mFragmentMenus[i] != null)
                    ft.hide(mFragmentMenus[i]);
            }
        }

    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.double_click_exit, Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }
}
