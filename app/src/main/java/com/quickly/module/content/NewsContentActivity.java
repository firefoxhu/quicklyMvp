package com.quickly.module.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.quickly.R;
import com.quickly.application.QuicklyApplication;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.module.base.BaseActivity;

public class NewsContentActivity extends BaseActivity {
    private static final String TAG = "NewsContentActivity";
    private static final String IMG = "img";

    public static void launch(NewsArticleItem.DataBean.ListBean bean) {
        QuicklyApplication.appContext.startActivity(new Intent(QuicklyApplication.appContext, NewsContentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static void launch(NewsArticleItem.DataBean.ListBean bean, String imgUrl) {
        QuicklyApplication.appContext.startActivity(new Intent(QuicklyApplication.appContext, NewsContentActivity.class)
                .putExtra(TAG, bean)
                .putExtra(IMG, imgUrl)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,
                        NewsContentFragment.newInstance(intent.getParcelableExtra(TAG), intent.getStringExtra(IMG)))
                .commit();
    }
}
