package com.quickly.module.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.quickly.R;
import com.quickly.module.mvp.base.MvpBaseActivity;

public class MvpMainActivity extends MvpBaseActivity {

    // TODO https://www.jianshu.com/p/bbb3b77d47eb

    private static final String TAG = "MainActivity";
    private long exitTime = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new NewsArticlePresenter(NewsArticleFragment.newInstance());
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
