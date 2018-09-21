package com.quickly.common;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.quickly.R;
import com.quickly.adapter.PhotoViewPagerAdapter;
import me.relex.circleindicator.CircleIndicator;

public class PhotoViewActivity extends AppCompatActivity implements PhotoViewPagerAdapter.IClickOnceCloseListener{


    private ViewPager mViewPager;

    private CircleIndicator mIndicator;


    public static final String PHOTO_LIST = "photo_list";

    public static final String POINTER_POSITION = "currentPosition";

    @Override
    protected void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_layout);

        mViewPager = findViewById(R.id.photo_view_pager);

        mIndicator = findViewById(R.id.photo_view_pager_indicator);

        PhotoViewPagerAdapter photoViewPagerAdapter = new PhotoViewPagerAdapter(getIntent().getStringArrayListExtra(PHOTO_LIST),PhotoViewActivity.this,this);
        mViewPager.setAdapter(photoViewPagerAdapter);
        photoViewPagerAdapter.registerDataSetObserver(mIndicator.getDataSetObserver());
        mIndicator.setViewPager(mViewPager);
        mViewPager.setCurrentItem(getIntent().getIntExtra(POINTER_POSITION,0));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 跳转动画
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

    @Override
    public void close() {
        finish();
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }
}
